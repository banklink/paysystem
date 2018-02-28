package com.banklink.lib.network;

import android.app.Application;
import android.util.Log;

import com.banklink.lib.config.ConfigInfo;
import com.banklink.lib.utils.Cfb_256crypt;
import com.banklink.lib.utils.MD5Util;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import okhttp3.OkHttpClient;

/**
 * Author：FynnJason
 * Describe：Network callback method.
 */

public class Net {

    public static void upPayCall(String data, String timestamp) {

        OkGo.<String>post(Api.UP_ORDER)
                .params(ConfigInfo.APP_ID_TAG, ConfigInfo.APP_ID)
                .params(ConfigInfo.TIME_STAMP_TAG, timestamp)
                .params(ConfigInfo.DATA_TAG, data)
                .params(ConfigInfo.SIGN_TAG, getSign(data, timestamp))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("Net", "onSuccess：pay");
                    }
                });
    }

    public static void upResultCall(String data, String timestamp) {

        OkGo.<String>post(Api.UP_ORDER)
                .params(ConfigInfo.APP_ID_TAG, ConfigInfo.APP_ID)
                .params(ConfigInfo.TIME_STAMP_TAG, timestamp)
                .params(ConfigInfo.DATA_TAG, data)
                .params(ConfigInfo.SIGN_TAG, getSign(data, timestamp))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("Net", "onSuccess：result");
                    }
                });
    }

    private static String getSign(String data, String timestamp) {
        String combination = "appId=" + ConfigInfo.APP_ID + "&data=" + data + "&ts=" + timestamp + ConfigInfo.APP_KEY;
        return MD5Util.getMD5(Cfb_256crypt.getSHA256StrJava(combination)).toLowerCase();
    }

    public static void init(Application app) {
        OkGo.getInstance().init(app).setOkHttpClient(new OkHttpClient.Builder().build());
    }
}
