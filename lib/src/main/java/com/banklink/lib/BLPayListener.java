package com.banklink.lib;

/**
 * Created by FynnJason.
 * Des：Payment callback method, including payment success, payment failure and payment cancellation.
 */

public interface BLPayListener {
    void paySuccess(String result);

    void payCancel();

    void payFailure();

}
