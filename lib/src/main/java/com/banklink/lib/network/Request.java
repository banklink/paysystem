package com.banklink.lib.network;

import android.annotation.SuppressLint;

import com.banklink.lib.config.ConfigInfo;
import com.banklink.lib.config.PotInfo;
import com.banklink.lib.config.PrInfo;
import com.banklink.lib.utils.Cfb_256crypt;
import com.banklink.lib.utils.TimeUtils;

/**
 * Created by FynnJason.
 * Des：The data transfer protocol, which is primarily the server authentication of the data that
 * will initiate payment and return payment results, guarantees the legality and security of payment.
 */

public class Request {
    private static String out_no, posCode, totalFee;

    public static void upPayOrder(PotInfo potInfo) {

        @SuppressLint("DefaultLocale")
        String timestamp = String.format("%010d", TimeUtils.getNowMills() / 1000);
        out_no = potInfo.getOutNo();
        posCode = potInfo.getPosCode();
        totalFee = potInfo.getTotalFee();
        String data = Cfb_256crypt.encrypt(ConfigInfo.ENCRYPT_KEY, potInfo.toString());

        Net.upPayCall(data, timestamp);
    }

    public static void upPayResult(String orderId, String status, String error_code) {
        @SuppressLint("DefaultLocale")
        String timestamp = String.format("%010d", TimeUtils.getNowMills() / 1000);
        PrInfo prInfo = new PrInfo();
        prInfo.setOutNo(out_no)
                .setPosCode(posCode)
                .setError_code(error_code)
                .setOrderId(orderId)
                .setTotalFee(totalFee)
                .setStatus(status);
        String data = Cfb_256crypt.encrypt(ConfigInfo.ENCRYPT_KEY, prInfo.toString());

        Net.upResultCall(data, timestamp);
    }


}
