package com.banklink.paysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.banklink.lib.BLPay;
import com.banklink.lib.config.ResultInfo;
import com.banklink.lib.listener.BLPayListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BLPay.pay("10006","1","www",MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BLPay.payResult(requestCode, resultCode, data, new BLPayListener() {
            @Override
            public void paySuccess(ResultInfo result) {

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
