package com.itti.apptest.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.itti.apptest.TestFirstActivity;
import com.itti.apptest.utils.NotificationUtil;

public class FirstNoticeBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到了第一条自定义广播", Toast.LENGTH_SHORT ).show();
        NotificationUtil notificationUtils = new NotificationUtil(context, TestFirstActivity.class);
        notificationUtils.sendNotification("第一条消息标题", "第一条消息的内容是：我是第一条消息");
    }
}
