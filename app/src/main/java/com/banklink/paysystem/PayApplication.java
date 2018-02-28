package com.banklink.paysystem;

import android.app.Application;

import com.banklink.lib.BLPay;

/**
 * Created by FynnJason.
 * Des：
 */

public class PayApplication extends Application {

    private static String appKey = "7d546ddd9223cfasf5df0e124266b092";
    private static String appId = "68ccdfadf66affab10ecdfc90abf8c62";
    private static String encryptKey = "6fa5616gg3ac92228a9dd9a0be5cac9f";

    @Override
    public void onCreate() {
        super.onCreate();
        BLPay.getInstance().init(this,appId,appKey,encryptKey);
    }
}
