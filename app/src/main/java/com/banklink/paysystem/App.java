package com.banklink.paysystem;

import android.app.Application;

import com.banklink.lib.BLPay;

/**
 * Author：FynnJason
 * Describe：Test
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BLPay.getInstance().init(this,"68ccdfadf66affab10ecdfc90abf8c62","7d546ddd9223cfasf5df0e124266b092","6fa5616gg3ac92228a9dd9a0be5cac9f");
    }
}
