package com.banklink.lib.config;

/**
 * Author：FynnJason
 * Describe：The data for the payment result.
 */

public class PrInfo {

    private String posCode;
    private String outNo;
    private String totalFee;
    private String orderId;
    private String status;
    private String error_code;

    public String getPosCode() {
        return posCode;
    }

    public PrInfo setPosCode(String posCode) {
        this.posCode = posCode;
        return this;
    }

    public String getOutNo() {
        return outNo;
    }

    public PrInfo setOutNo(String outNo) {
        this.outNo = outNo;
        return this;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public PrInfo setTotalFee(String totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PrInfo setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PrInfo setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getError_code() {
        return error_code;
    }

    public PrInfo setError_code(String error_code) {
        this.error_code = error_code;
        return this;
    }

    @Override
    public String toString() {
        return "{" + "\"pos_code\":\"" + getPosCode() + "\"" +
                ", \"out_no\":\"" + getOutNo() + "\"" +
                ", \"total_fee\":\"" + getTotalFee() + "\"" +
                ", \"ord_no\":\"" + getOrderId() + "\"" +
                ", \"status\":\"" + getStatus() + "\"" +
                ", \"error_code\":\"" + getError_code() + "\"" +
                '}';
    }
}
