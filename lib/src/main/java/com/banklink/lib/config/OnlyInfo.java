package com.banklink.lib.config;

/**
 * Created by FynnJason.
 * Des：This is an isolated entity class, saving only the information we need.
 */

public class OnlyInfo {
    private String orderId;

    private String serialNum;

    private String voucherNo;

    public String getOrderId() {
        return orderId;
    }

    public OnlyInfo setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public OnlyInfo setSerialNum(String serialNum) {
        this.serialNum = serialNum;
        return this;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public OnlyInfo setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "orderId:'" + orderId + '\'' +
                ", serialNum:'" + serialNum + '\'' +
                ", voucherNo:'" + voucherNo + '\'' +
                '}';
    }
}
