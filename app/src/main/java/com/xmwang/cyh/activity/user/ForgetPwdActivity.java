package com.xmwang.cyh.activity.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xmwang.cyh.BaseActivity;
import com.xmwang.cyh.R;
import com.xmwang.cyh.common.Common;
import com.xmwang.cyh.common.Data;
import com.xmwang.cyh.common.RetrofitHelper;
import com.xmwang.cyh.common.SADialog;
import com.xmwang.cyh.model.BaseModel;
import com.xmwang.cyh.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.txt_phone)
    EditText txtPhone;
    @BindView(R.id.txt_new_pwd)
    EditText txtNewPwd;
    @BindView(R.id.txt_code)
    EditText txtCode;
    @BindView(R.id.btn_send_code)
    TextView btnSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SADialog.instance.hideProgress();
    }

    @OnClick({R.id.title_back, R.id.btn_send_code, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_send_code:
                sendCode();
                break;
            case R.id.btn_done:
                forgetPwd();
                break;
        }
    }
    private void sendCode(){
        if (!Common.isMobile(txtPhone.getText().toString())){
            ToastUtils.getInstance().toastShow("手机号不正确");
            return;
        }

        Call<BaseModel> call = RetrofitHelper.instance.getApiUserService().sendCode(
                Data.instance.AdminId,
                txtPhone.getText().toString().trim()
        );
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                BaseModel baseModel = response.body();
                if (baseModel.getCode() != RetrofitHelper.instance.SUCCESS_CODE) {
                    return;
                }
                btnSendCode.setEnabled(false);
                timer.start();
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                ToastUtils.getInstance().toastShow("找回密码失败");
            }
        });
    }
    public CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnSendCode.setText("重试(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            btnSendCode.setEnabled(true);
            btnSendCode.setText("发送验证码");
        }
    };

    private void forgetPwd(){
        if (!Common.isMobile(txtPhone.getText().toString())){
            ToastUtils.getInstance().toastShow("手机号不正确");
            return;
        }
        if (TextUtils.isEmpty(txtNewPwd.getText())){
            ToastUtils.getInstance().toastShow("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(txtCode.getText())){
            ToastUtils.getInstance().toastShow("请输入验证码");
            return;
        }
        SADialog.instance.showProgress(this);
        Call<BaseModel> call = RetrofitHelper.instance.getApiUserService().forgetPwd(
                Data.instance.AdminId,
                txtPhone.getText().toString().trim(),
                txtNewPwd.getText().toString().trim(),
                txtCode.getText().toString().trim()
        );
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                BaseModel baseModel = response.body();
                SADialog.instance.hideProgress();
                ToastUtils.getInstance().toastShow(baseModel.getMessage());
                if (baseModel.getCode() != RetrofitHelper.instance.SUCCESS_CODE) {
                    return;
                }
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e) {}
                finish();
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                SADialog.instance.hideProgress();
                ToastUtils.getInstance().toastShow("找回密码失败");
            }
        });
    }
}
