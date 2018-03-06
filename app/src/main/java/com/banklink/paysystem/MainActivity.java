package com.banklink.paysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.banklink.lib.BLPay;
import com.banklink.lib.config.ResultInfo;
import com.banklink.lib.listener.BLPayListener;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int i = r.nextInt(10000000);
                BLPay.pay(String.valueOf(i), "1", "www.baidu.com", MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BLPay.payResult(requestCode, resultCode, data, new BLPayListener() {
            @Override
            public void paySuccess(ResultInfo resultInfo) {
            }

            @Override
            public void payCancel() {
            }

            @Override
            public void payFailure() {
            }
        });
    }
}
