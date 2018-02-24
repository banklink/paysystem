package com.banklink.lib;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.banklink.lib.config.ConfigInfo;
import com.banklink.lib.config.PayInfo;
import com.banklink.lib.utils.Utils;
import com.lzy.okgo.OkGo;

import okhttp3.OkHttpClient;

/**
 * Created by FynnJason.
 * <p>
 * Describe：This is the payment initialization class, which initializes some parameters here, including
 * the method of network request, obtaining some parameter information of the application, and so on.
 */

public class BLPay {

    private BLPay() {
    }

    private static class Holder {
        private static BLPay INSTANCE = new BLPay();
    }

    public static BLPay getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Initialize some basic configuration of the application.
     *
     * @param app Application
     */
    public void init(Application app) {
        OkGo.getInstance().init(app).setOkHttpClient(new OkHttpClient.Builder().build());
        Utils.init(app);
    }

    public static void pay(String orderId, String orderAmt, String bgRetUrl, Activity activity) {
        if (TextUtils.isEmpty(orderId) && TextUtils.isEmpty(orderAmt) && TextUtils.isEmpty(bgRetUrl)) {
            throw new NullPointerException("orderId or orderAmt or bgRetUrl is null");
        } else {
            PayInfo info = new PayInfo();
            info.setOrderId(orderId).setOrderAmt(orderAmt).setBgRetUrl(bgRetUrl);
            Intent intent = new Intent();
            intent.setClassName(ConfigInfo.PACKAGE_NAME, ConfigInfo.CLASS_NAME);
            Bundle bundle = new Bundle();
            bundle.putString(ConfigInfo.PAY_KEY, info.toString());
            intent.putExtras(bundle);
            activity.startActivityForResult(intent, ConfigInfo.REQ_ID);
        }
    }

}
