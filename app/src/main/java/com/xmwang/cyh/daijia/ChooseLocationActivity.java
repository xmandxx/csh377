package com.xmwang.cyh.daijia;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.xmwang.cyh.BaseActivity;
import com.xmwang.cyh.R;
import com.xmwang.cyh.common.Data;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseLocationActivity extends BaseActivity implements AMap.OnMyLocationChangeListener, AMap.OnCameraChangeListener, GeocodeSearch.OnGeocodeSearchListener, Inputtips.InputtipsListener, PoiSearch.OnPoiSearchListener,AdapterView.OnItemClickListener {

    @BindView(R.id.title_text)
    EditText titleText;
    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.list_view)
    ListView listView;

    AMap aMap;
    MyLocationStyle myLocationStyle;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    Marker marker;
    GeocodeSearch geocoderSearch;
    String city;
    Location currLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);
        ButterKnife.bind(this);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);
        init();
    }

    @OnClick({R.id.title_back, R.id.title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_right:
                break;
        }
    }

    private void init() {
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        //设置希望展示的地图缩放级别
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(15);
        aMap.animateCamera(mCameraUpdate);

        //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        if (myLocationStyle == null) {
            myLocationStyle = new MyLocationStyle();
        }
        myLocationStyle.showMyLocation(true);  //隐藏定位蓝点
        myLocationStyle.isMyLocationShowing();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);

        //
        aMap.setMyLocationStyle(myLocationStyle);
        //设置定位监听
        aMap.setOnMyLocationChangeListener(this);
        aMap.setOnCameraChangeListener(this);


        marker = aMap.addMarker(new MarkerOptions());

        //逆地理
        if (geocoderSearch == null) {
            geocoderSearch = new GeocodeSearch(this);
        }
        geocoderSearch.setOnGeocodeSearchListener(this);

    }


    //反地理编码
    private void regeocodeQuery(double latitude, double longitude) {
        LatLonPoint point = new LatLonPoint(latitude, longitude);
        RegeocodeQuery query = new RegeocodeQuery(point, 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }

    @Override
    public void onMyLocationChange(Location location) {
        // 定位回调监听
        if (location != null) {
            //设置希望展示的地图缩放级别
            CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(15);
            aMap.animateCamera(mCameraUpdate);
            currLocation = location;
            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle bundle = location.getExtras();
            if (bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);

                /*
                errorCode
                errorInfo
                locationType
                */
//                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType );
                if (errorCode > 0) {
                    Toast.makeText(this, "定位失败，请打开定位权限", Toast.LENGTH_SHORT).show();
                }
                regeocodeQuery(location.getLatitude(), location.getLongitude());
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                marker.setPosition(latLng);
                //
                //调取一次获取周边
                searchPOI();

            } else {
                Log.e("amap", "定位信息， bundle is null ");

            }

        } else {
            Log.e("amap", "定位失败");
            Toast.makeText(this, "定位失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        regeocodeQuery(cameraPosition.target.latitude, cameraPosition.target.longitude);
        marker.setPosition(cameraPosition.target);
    }

    @Override
    public void onCameraChangeFinish(CameraPosition position) {


    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        //解析result获取地址描述信息
//        startLocation.setText(result.getRegeocodeAddress().getFormatAddress());
        city = result.getRegeocodeAddress().getCity();
        Data.instance.setFormatAddress(result.getRegeocodeAddress().getFormatAddress());
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    //poi
    InputtipsQuery inputquery;
    PoiSearch poiSearch;

    /**
     * 获取周边信息
     */
    private void searchPOI() {
        if (poiSearch == null) {
            poiSearch = new PoiSearch(this, null);
            poiSearch.setOnPoiSearchListener(this);
        }
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(currLocation.getLatitude(), currLocation.getLongitude()), 1000));
        poiSearch.searchPOIAsyn();

    }


    @Override
    public void onPoiSearched(PoiResult poiResult, int rCode) {
        Log.e("xmwang","onPoiSearched");
        List<PoiItem> poiItems = poiResult.getPois();
        listView.removeAllViews();
        final List<String> data = new ArrayList<String>();
        for (PoiItem poiItem : poiItems){
            data.add(poiItem.getSnippet());
        }
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data));
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        Log.e("xmwang","onPoiItemSearched");
    }

    //输入内容自动提示
    private void bindText(String newText){
        InputtipsQuery inputquery = new InputtipsQuery(newText, city);
        inputquery.setCityLimit(true);
        Inputtips inputTips = new Inputtips(this, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }
    @Override
    public void onGetInputtips(List<Tip> list, int rCode) {
        Log.e("xmwang","onGetInputtips");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("xmwang","onItemClick");
    }
}
