package com.itti.apptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestSecondActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "Tonsen_Tag";
    Button goBackBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_second);

        goBackBtn2 = findViewById(R.id.btnGoBack2);
        goBackBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGoBack2:
                //创建一个Intent的实例
                Intent intent = new Intent();
                //设置从哪个activity启动哪个activity
                intent.setClass(TestSecondActivity.this, MainActivity.class);
                //把一个值写入到Intent中
                intent.putExtra("SwitchActivity", "MainActivity");
                //启动另一个activity
                TestSecondActivity.this.startActivity(intent);
                break;
        }
    }
}
