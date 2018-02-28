package com.banklink.lib.network;

import android.annotation.SuppressLint;
import android.util.Log;

import com.banklink.lib.config.ResultInfo;
import com.banklink.lib.utils.Cfb_256crypt;
import com.banklink.lib.utils.MD5Util;
import com.banklink.lib.utils.TimeUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by FynnJason.
 * Des：The data transfer protocol, which is primarily the server authentication of the data that
 * will initiate payment and return payment results, guarantees the legality and security of payment.
 */

public class Request {

    private static String appKey = "7d546ddd9223cfasf5df0e124266b092";
    private static String appId = "68ccdfadf66affab10ecdfc90abf8c62";
    private static String encryptKey = "6fa5616gg3ac92228a9dd9a0be5cac9f";

    public static void upPayOrder() {

        @SuppressLint("DefaultLocale")
        String timestamp = String.format("%010d", TimeUtils.getNowMills() / 1000);

        String json = "{" + "\"pos_code\":\"" + "rdg1201\"" +
                ", \"out_no\":\"" + "10005\"" +
                ", \"total_fee\":\"" + "1" + "\"" +
                '}';
        Log.e("Request", "json：" + json);

        String data = Cfb_256crypt.encrypt(encryptKey, json);

        Log.e("Request", "data：" + data);

        String addMi = "appId=" + appId + "&data=" + data + "&ts=" + timestamp + appKey;

        Log.e("Request", "addMi：" + addMi);

        String sign = MD5Util.getMD5(Cfb_256crypt.getSHA256StrJava(addMi)).toLowerCase();

        Log.e("Request", "sign：" + sign);

        OkGo.<String>post("http://test.beikelin.com/paycard/")
                .params("appId", appId)
                .params("ts", timestamp)
                .params("data", data)
                .params("sign", sign)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("Request", "onSuccess：" + response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.e("Request", "onError：" + response.toString());
                    }
                });
    }

    public static void upPayResult(ResultInfo resultInfo) {

    }
}
