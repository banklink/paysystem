package com.banklink.lib;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.banklink.lib.config.ConfigInfo;
import com.banklink.lib.config.PayInfo;
import com.banklink.lib.config.PotInfo;
import com.banklink.lib.config.ResultInfo;
import com.banklink.lib.listener.BLPayListener;
import com.banklink.lib.network.Net;
import com.banklink.lib.network.Request;
import com.banklink.lib.service.BLService;
import com.banklink.lib.utils.AppUtils;
import com.banklink.lib.utils.Utils;
import com.google.gson.Gson;
import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.utils.SystemInfomation;


/**
 * Created by FynnJason.
 * <p>
 * Describe：This is the payment initialization class, which initializes some parameters here, including
 * the method of network request, obtaining some parameter information of the application, and so on.
 */

public class BLPay extends BLService {

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
    public void init(Application app, String appId, String appKey, String encryptKey) {
        initConfig(app, appId, appKey, encryptKey);
    }

    /**
     * Complete configuration initialization data.
     *
     * @param app Application
     */
    private void initConfig(Application app, String appId, String appKey, String encryptKey) {
        Net.init(app);
        Utils.init(app);
        if (TextUtils.isEmpty(ConfigInfo.TEST)) {
            ConfigInfo.POS_CODE = SystemInfomation.getDeviceInfo().getSpecialSerialNo();
        } else {
            ConfigInfo.POS_CODE = "rdg1201";
        }
        ConfigInfo.APP_NAME = AppUtils.getAppName();
        ConfigInfo.APP_KEY = appKey;
        ConfigInfo.APP_ID = appId;
        ConfigInfo.ENCRYPT_KEY = encryptKey;
    }

    /**
     * The method of setting up a transaction.
     *
     * @param orderId  The order number
     * @param orderAmt The order amount
     * @param bgRetUrl Transaction callback url.
     * @param activity Context activity
     */
    public static void pay(String orderId, String orderAmt, String bgRetUrl, Activity activity) {
        if (TextUtils.isEmpty(orderId) && TextUtils.isEmpty(orderAmt) && TextUtils.isEmpty(bgRetUrl)) {
            throw new NullPointerException("orderId or orderAmt or bgRetUrl is null");
        } else {
            PotInfo potInfo = new PotInfo();
            potInfo.setPosCode(ConfigInfo.POS_CODE).setOutNo(orderId).setTotalFee(orderAmt);
            Request.upPayOrder(potInfo);
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

    /**
     * Payment callback method, including payment success, payment failure and payment cancellation.
     *
     * @param requestCode The originating request code.
     * @param resultCode  Payment result code
     * @param data        Payment result data
     * @param listener    Pay the callback listener.
     */
    public static void payResult(int requestCode, int resultCode, Intent data, BLPayListener listener) {
        //Use the equipment to log out the main control service.
        //Release financial equipment to make other applications available.
        //To use financial equipment.
        DeviceService.logout();
        if (requestCode == ConfigInfo.REQ_ID) {
            switch (resultCode) {
                case ConfigInfo.RESULT_SUCCESS:
                    if (data.hasExtra(ConfigInfo.PAY_RESULT)) {
                        String resultJson = data.getStringExtra(ConfigInfo.PAY_RESULT);
                        ResultInfo result = new Gson().fromJson(resultJson, ResultInfo.class);
                        Request.upPayResult(result.getOrderId(), ConfigInfo.PAY_SUCCESS, String.valueOf(ConfigInfo.RESULT_SUCCESS));
                        listener.paySuccess(result);
                    }
                    break;
                case ConfigInfo.RESULT_CANCEL:
                    Request.upPayResult(ConfigInfo.NO_STRING_DATA, ConfigInfo.PAY_CANCEL, String.valueOf(ConfigInfo.RESULT_CANCEL));
                    listener.payCancel();
                    break;
                case ConfigInfo.RESULT_FAILURE:
                    Request.upPayResult(ConfigInfo.NO_STRING_DATA, ConfigInfo.PAY_FAILURE, String.valueOf(ConfigInfo.RESULT_FAILURE));
                    listener.payFailure();
                    break;
            }
        }
    }


}
