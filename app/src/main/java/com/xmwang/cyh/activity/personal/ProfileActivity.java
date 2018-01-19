package com.xmwang.cyh.activity.personal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmwang.cyh.BaseActivity;
import com.xmwang.cyh.R;
import com.xmwang.cyh.common.Data;
import com.xmwang.cyh.common.RetrofitHelper;
import com.xmwang.cyh.model.BaseModel;
import com.xmwang.cyh.model.UserInfo;
import com.xmwang.cyh.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.btn_img)
    ImageView btnImg;
    @BindView(R.id.txt_real_name)
    EditText txtRealName;
    @BindView(R.id.txt_cy_name)
    EditText txtCyName;
    @BindView(R.id.txt_gender)
    TextView txtGender;
    @BindView(R.id.ll_gender)
    LinearLayout llGender;
    private String gender = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        retrofit2.Call<UserInfo> call = RetrofitHelper.instance.getApiUserService().getuserinfo(
                Data.instance.AdminId,
                Data.instance.getUserId()
        );

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(retrofit2.Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo model = response.body();
                if (model.getCode() != RetrofitHelper.instance.SUCCESS_CODE) {
                    return;
                }
                if (model.getData().size() > 0) {
                    UserInfo.DataBean uModel = model.getData().get(0);
                    txtRealName.setText(uModel.getReal_name());
                    txtCyName.setText(uModel.getUser_name());
                    txtGender.setText(uModel.getSex() == 1 ? "男" : "女");
                    gender = String.valueOf(uModel.getSex());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserInfo> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.title_back, R.id.btn_img, R.id.ll_profile, R.id.ll_gender, R.id.ll_qcode, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_img:
                break;
            case R.id.ll_profile:
                break;
            case R.id.ll_gender:
                break;
            case R.id.ll_qcode:
                break;
            case R.id.btn_save:
                save();
                break;
        }
    }
    private void genderAction(){
        String[] items = {"男","女"};
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("选择性别")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 1){
                            gender = "1";
                            txtGender.setText("男");
                        }else{
                            gender = "0";
                            txtGender.setText("女");
                        }
                    }
                }).create();
        dialog.show();
    }
    private void save(){
        if (TextUtils.isEmpty(txtRealName.getText())){
            ToastUtils.getInstance().toastShow("请输入真实姓名");
            return;
        }
        if (TextUtils.isEmpty(txtCyName.getText())){
            ToastUtils.getInstance().toastShow("请输入车友名");
            return;
        }
        retrofit2.Call<BaseModel> call = RetrofitHelper.instance.getApiUserService().editUser(
                Data.instance.getUserId(),
                "",
                txtRealName.getText().toString(),
                txtCyName.getText().toString(),
                gender
        );

        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(retrofit2.Call<BaseModel> call, Response<BaseModel> response) {
                BaseModel model = response.body();
                ToastUtils.getInstance().toastShow(model.getMessage());
                if (model.getCode() != RetrofitHelper.instance.SUCCESS_CODE) {
                    return;
                }
                finish();
            }

            @Override
            public void onFailure(retrofit2.Call<BaseModel> call, Throwable t) {

            }
        });
    }

}
