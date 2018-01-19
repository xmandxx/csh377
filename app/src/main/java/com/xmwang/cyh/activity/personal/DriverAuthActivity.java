package com.xmwang.cyh.activity.personal;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.xmwang.cyh.BaseActivity;
import com.xmwang.cyh.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverAuthActivity extends BaseActivity {

    @BindView(R.id.txt_real_name)
    EditText txtRealName;
    @BindView(R.id.txt_id_number)
    EditText txtIdNumber;
    @BindView(R.id.img_id_front)
    ImageView imgIdFront;
    @BindView(R.id.img_id_back)
    ImageView imgIdBack;
    @BindView(R.id.img_driving)
    ImageView imgDriving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_driver_auth);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_back, R.id.img_id_front, R.id.img_id_back, R.id.img_driving, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.img_id_front:
                break;
            case R.id.img_id_back:
                break;
            case R.id.img_driving:
                break;
            case R.id.btn_submit:
                submit();
                break;
        }
    }
    private void submit(){

    }

}
