package com.banklink.lib.config;

/**
 * Author：FynnJason
 * Describe：POS terminal serial number, order number, total order amount.
 */

public class PotInfo {
    private String posCode;
    private String outNo;
    private String totalFee;

    public String getPosCode() {
        return posCode;
    }

    public PotInfo setPosCode(String posCode) {
        this.posCode = posCode;
        return this;
    }

    public String getOutNo() {
        return outNo;
    }

    public PotInfo setOutNo(String outNo) {
        this.outNo = outNo;
        return this;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public PotInfo setTotalFee(String totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    @Override
    public String toString() {
        return "{" + "\"pos_code\":\"" + getPosCode() + "\"" +
                ", \"out_no\":\"" + getOutNo() + "\"" +
                ", \"total_fee\":\"" + getTotalFee() + "\"" +
                '}';
    }
}
