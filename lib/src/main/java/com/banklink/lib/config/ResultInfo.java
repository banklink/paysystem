package com.banklink.lib.config;

/**
 * Created by FynnJason.
 * Des：This is the list of parameters that will be successfully returned, and the information
 * contained in it will be displayed in the printed credential, including orders, running water,
 * transaction vouchers, and so on.
 */

public class ResultInfo {

    private String bankName;
    private String batchId;
    private String merName;
    private String serialNum;
    private String orderAmt;
    private String orderId;
    private String payCardId;
    private String refNo;
    private String resultCode;
    private String tradeDesc;
    private String tradeType;
    private String transDate;
    private String transTime;
    private String voucherNo;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayCardId() {
        return payCardId;
    }

    public void setPayCardId(String payCardId) {
        this.payCardId = payCardId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "bankName='" + bankName + '\'' +
                ", batchId='" + batchId + '\'' +
                ", merName='" + merName + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", orderAmt='" + orderAmt + '\'' +
                ", orderId='" + orderId + '\'' +
                ", payCardId='" + payCardId + '\'' +
                ", refNo='" + refNo + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", tradeDesc='" + tradeDesc + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", transDate='" + transDate + '\'' +
                ", transTime='" + transTime + '\'' +
                ", voucherNo='" + voucherNo + '\'' +
                '}';
    }
}
