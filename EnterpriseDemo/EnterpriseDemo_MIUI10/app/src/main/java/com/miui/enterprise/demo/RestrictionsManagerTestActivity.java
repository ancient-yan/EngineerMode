package com.miui.enterprise.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.miui.enterprise.sdk.RestrictionsManager;

public class RestrictionsManagerTestActivity extends AppCompatActivity {

    private RestrictionsManager mRestrictionsManager;

    private static SparseArray<String> sMsgArray = new SparseArray<String>() {{
        put(RestrictionsManager.DISABLE, "disable");
        put(RestrictionsManager.ENABLE, "enable");
        put(RestrictionsManager.OPEN, "open");
        put(RestrictionsManager.CLOSE, "close");
        put(RestrictionsManager.FORCE_OPEN, "force open");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrictions_manager_test);
        mRestrictionsManager = RestrictionsManager.getInstance();
    }

    public void setWifiStatus(View v) {
        changeStatus(RestrictionsManager.WIFI_STATE);
    }

    public void disallowVPN(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_VPN, "VPN");
    }

    public void disallowTether(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_TETHER, "Tether");
    }

    public void setBluetoothStatus(View v) {
        changeStatus(RestrictionsManager.BLUETOOTH_STATE);
    }

    public void setGPSStatus(View v) {
        changeStatus(RestrictionsManager.GPS_STATE);
    }

    public void disallowCamera(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_CAMERA, "Camera");
    }

    // 录音
    public void disallowMicrophone(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_MICROPHONE, "Microphone");
    }

    public void disallowScreencapture(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_SCREENCAPTURE, "Screen capture");
    }

    public void disallowSdcard(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_SDCARD, "SDCard");
    }

    public void disallowMTP(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_MTP, "MTP");
    }

    public void setNFCStatus(View v) {
        changeStatus(RestrictionsManager.NFC_STATE);
    }

    public void disallowOTG(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_OTG, "OTG");
    }

    public void disallowFingerprint(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_FINGERPRINT, "FingerPrint");
    }

    public void disallowUSBDebug(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_USBDEBUG, "USB Debug");
    }

    public void disallowFactoryReset(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_FACTORYRESET, "Factory reset");
    }

    public void disallowTimeSet(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_TIMESET, "Time set");
    }

    public void disallowIMEIRead(View v) {
        toggleRestriction(RestrictionsManager.DISALLOW_IMEIREAD, "IMEI read");
    }

    public void disableAccelerometer(View v) {
        toggleRestriction(RestrictionsManager.DISABLE_ACCELEROMETER, "Accelerometer");
    }

    private void toggleRestriction(String key, String msg) {
        boolean hasRestriction = mRestrictionsManager.hasRestriction(key);
        mRestrictionsManager.setRestriction(key, !hasRestriction);
        Toast.makeText(this, msg + ": " + !hasRestriction, Toast.LENGTH_SHORT).show();
    }

    private void changeStatus(String key) {
        int state = mRestrictionsManager.getControlStatus(key);
        int newState = convertState(state);
        mRestrictionsManager.setControlStatus(key, newState);
        Toast.makeText(this, sMsgArray.get(newState), Toast.LENGTH_SHORT).show();
    }

    private int convertState(int originState) {
        int state = RestrictionsManager.ENABLE;
        switch (originState) {
            case RestrictionsManager.DISABLE:
                state = RestrictionsManager.ENABLE;
                break;
            case RestrictionsManager.ENABLE:
                state = RestrictionsManager.OPEN;
                break;
            case RestrictionsManager.OPEN:
                state = RestrictionsManager.CLOSE;
                break;
            case RestrictionsManager.CLOSE:
                state = RestrictionsManager.FORCE_OPEN;
                break;
            case RestrictionsManager.FORCE_OPEN:
                state = RestrictionsManager.DISABLE;
                break;
        }
        return state;
    }
}
