package com.banklink.paysystem;

import android.app.Application;

import com.banklink.lib.BLPay;

/**
 * Created by FynnJason.
 * Des：
 */

public class PayApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BLPay.getInstance().init(this);
    }
}
