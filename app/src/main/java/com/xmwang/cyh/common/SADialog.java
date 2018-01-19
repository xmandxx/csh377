package com.xmwang.cyh.common;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by xmwang on 2018/1/4.
 */

public enum SADialog {
    instance;
    SweetAlertDialog pDialog;

    public void showProgress(Context context) {
//        if (pDialog == null){
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
//        }
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void hideProgress() {
        if (pDialog != null){
            pDialog.dismiss();
            pDialog.cancel();
        }
    }
}
