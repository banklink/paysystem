package com.banklink.lib.service;

import android.app.Activity;
import android.os.Handler;

import com.banklink.lib.config.ConfigInfo;
import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.exception.ReloginException;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.exception.ServiceOccupiedException;
import com.landicorp.android.eptapi.exception.UnsupportMultiProcess;

/**
 * Created by FynnJason.
 * Des：Prior to the use of the various device interfaces provided by EPT, the right to use the
 * device must be obtained.The application is provided through EPT.BLService, a financial
 * equipment service, ACTS like the right to access and release equipment.
 */

public class BLService {

    //login
    public void bindDeviceService(final Activity activity) {
        try {
            DeviceService.login(activity);
        } catch (RequestException e) {
            // Rebind after a few milliseconds,
            // If you want this application keep the right of the device service
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bindDeviceService(activity);
                }
            }, ConfigInfo.DELAY_300);

            e.printStackTrace();
        } catch (ServiceOccupiedException e) {
            e.printStackTrace();
        } catch (ReloginException e) {
            e.printStackTrace();
        } catch (UnsupportMultiProcess e) {
            e.printStackTrace();
        }
    }

    //logout
    public void unbindDeviceService() {
        DeviceService.logout();
    }
}
