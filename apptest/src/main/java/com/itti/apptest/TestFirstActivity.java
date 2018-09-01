package com.itti.apptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestFirstActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "Tonsen_Tag";
    Button goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_first);

        goBackBtn = findViewById(R.id.btnGoBack);
        goBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoBack:
                //创建一个Intent的实例
                Intent intent = new Intent();
                //设置从哪个activity启动哪个activity
                intent.setClass(TestFirstActivity.this, MainActivity.class);
                //把一个值写入到Intent中
                intent.putExtra("SwitchActivity", "MainActivity");
                //启动另一个activity
                TestFirstActivity.this.startActivity(intent);
                break;
        }
    }
}
