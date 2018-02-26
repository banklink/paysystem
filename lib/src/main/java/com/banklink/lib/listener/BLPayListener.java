package com.banklink.lib.listener;

import com.banklink.lib.config.ResultInfo;

/**
 * Created by FynnJason.
 * Des：Payment callback method, including payment success, payment failure and payment cancellation.
 */

public interface BLPayListener {
    void paySuccess(ResultInfo result);

    void payCancel();

    void payFailure();

}
