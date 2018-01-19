package com.xmwang.cyh.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by xmWang on 2017/12/25.
 */

public class MyReceiver extends BroadcastReceiver {
    private OnReceiverMessage message;

    @Override
    public void onReceive(Context context, Intent intent) {
        message.onMessage(intent);
    }

    public interface OnReceiverMessage {
        public void onMessage(Intent intent);
    }

    public void setMessage(OnReceiverMessage message) {
        this.message = message;
    }

}
