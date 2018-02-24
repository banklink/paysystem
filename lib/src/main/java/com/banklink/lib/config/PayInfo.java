package com.banklink.lib.config;

import android.text.TextUtils;

import com.banklink.lib.utils.AppUtils;

/**
 * Created by FynnJason.
 * <p>
 * Describe：A payment transaction request, the need to some of the parameters, which may include
 * business types, the order number, order total amount, merchants, transaction type, certificate
 * number, logic terminal number, transaction callback URL, the name of the App, the App
 * identification, there are other note information, some of these parameters are optional,
 * some of them are mandatory, developers according to need to fill in, please.
 */

public class PayInfo {

    public PayInfo() {
    }

    private String orderId;

    private String orderAmt;

    private String memberId;

    private String pnrDevId;

    private String bgRetUrl;

    private String tradeType;

    private String remarks;

    private String appName;

    private String appKey;

    public PayInfo setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }


    public PayInfo setBgRetUrl(String bgRetUrl) {
        this.bgRetUrl = bgRetUrl;
        return this;
    }

    public PayInfo setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
        return this;
    }

    @Override
    public String toString() {
        setDefault();
        return "{" +
                "orderId:'" + orderId + '\'' +
                ", orderAmt:'" + orderAmt + '\'' +
                ", memberId:'" + memberId + '\'' +
                ", pnrDevId:'" + pnrDevId + '\'' +
                ", bgRetUrl:'" + bgRetUrl + '\'' +
                ", tradeType:'" + tradeType + '\'' +
                ", remarks:'" + remarks + '\'' +
                ", appName:'" + appName + '\'' +
                ", appKey:'" + appKey + '\'' +
                '}';
    }

    /**
     * To pay some empty values to the default configuration parameters, has been helping developers
     * to get into the application name, applications of signature, and other information,
     * developers need to focus only on the number of incoming orders, the order amount and pay
     * the callback url
     */
    private void setDefault() {
        if (TextUtils.isEmpty(appName)) {
            appName = AppUtils.getAppName();
        }
        if (TextUtils.isEmpty(remarks)) {
            remarks = appName;
        }
        if (TextUtils.isEmpty(memberId)) {
            memberId = ConfigInfo.NO_STRING_DATA;
        }
        if (TextUtils.isEmpty(pnrDevId)) {
            pnrDevId = ConfigInfo.NO_STRING_DATA;
        }
        if (TextUtils.isEmpty(appKey)) {
            appKey = AppUtils.getAppSignatureSHA1().replaceAll(ConfigInfo.SEMICOLON, ConfigInfo.NO_STRING_DATA).toLowerCase();
        }
        if (TextUtils.isEmpty(tradeType)) {
            tradeType = ConfigInfo.BANK_CARD;
        }
    }
}
