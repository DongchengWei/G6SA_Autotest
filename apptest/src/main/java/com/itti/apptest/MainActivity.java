package com.itti.apptest;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.itti.apptest.broadcasts.FirstNoticeBroadcast;
import com.itti.apptest.utils.NotificationUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String TAG = "Tonsen_Tag";
    Button sendBtn;
    Button sendBtn2;

    private IntentFilter mIntentFilter = null;
    private FirstNoticeBroadcast firstNoticeBroadcast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        sendBtn = findViewById(R.id.btn1);
        sendBtn.setOnClickListener(this);
        sendBtn2 = findViewById(R.id.btn2);
        sendBtn2.setOnClickListener(this);

        //过滤器
        mIntentFilter = new IntentFilter("FirstNotice");
        //创建广播接收者的对象
        firstNoticeBroadcast =  new FirstNoticeBroadcast();
        //注册广播接收者的对象
        registerReceiver(firstNoticeBroadcast, mIntentFilter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Log.d(TAG, "onClick: btn1 click");
                NotificationUtil notificationUtils = new NotificationUtil(this, TestFirstActivity.class);
                notificationUtils.sendNotification("第一条消息标题", "第一条消息的内容是：我是第一条消息");

                Intent intent = new Intent("FirstNotice");
                //发送一个广播
                sendBroadcast(intent);

                break;
            case R.id.btn2:
                Log.d(TAG, "onClick: btn2 click");
                NotificationUtil notif = new NotificationUtil(this, TestSecondActivity.class);
                notif.sendNotification("这是第二条的标题", "第二条的内容是：我是第二条消息");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消广播接收者的注册
        unregisterReceiver(firstNoticeBroadcast);
    }

}
