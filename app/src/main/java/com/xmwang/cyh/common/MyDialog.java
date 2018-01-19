package com.xmwang.cyh.common;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xmwang.cyh.R;

/**
 * Created by xmWang on 2017/12/25.
 */

public class MyDialog {
    //点击确认按钮回调接口
    public interface OnConfirmListener {
        public void onConfirmClick();
    }

    /**
     * @param activity
     * @param content         提示内容
     * @param confirmListener void * @throws
     * @Title: show
     * @Description: 显示Dialog
     */
    public static void show(Activity activity, String content, final OnConfirmListener confirmListener) {
        // 加载布局文件
        View view = View.inflate(activity, R.layout.view_dialog, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        if (!Common.isNullOrEmpty(content)) {
            text.setText(content);
        }
        // 创建Dialog
        final AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setCancelable(false);
        // 设置点击dialog以外区域不取消Dialog
        dialog.show();
        dialog.setContentView(view);
        dialog.getWindow().setLayout(Common.getWindowWidth(activity) / 3 * 2
                , ActionBar.LayoutParams.WRAP_CONTENT);
        //设置弹出框宽度为屏幕宽度的三分之二
        // 确定
        confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                confirmListener.onConfirmClick();
            }
        });
        // 取消
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
