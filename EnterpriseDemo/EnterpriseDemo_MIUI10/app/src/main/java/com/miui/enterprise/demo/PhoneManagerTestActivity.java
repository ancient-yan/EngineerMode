package com.miui.enterprise.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.miui.enterprise.sdk.PhoneManager;

import java.util.ArrayList;
import java.util.List;

public class PhoneManagerTestActivity extends AppCompatActivity {

    private PhoneManager mPhoneManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_manager_test);
        mPhoneManager = PhoneManager.getInstance();
    }

    public void controlSMS(View v) {
        if (mPhoneManager.getSMSStatus() == PhoneManager.FLAG_DEFAULT) {
            mPhoneManager.controlSMS(PhoneManager.FLAG_DISALLOW_OUT | PhoneManager.FLAG_DISALLOW_IN);
            Toast.makeText(this, "Disallow SIM0 in, SIM0 out", Toast.LENGTH_SHORT).show();
        } else {
            mPhoneManager.controlSMS(PhoneManager.FLAG_DEFAULT);
            Toast.makeText(this, "Control nothing", Toast.LENGTH_SHORT).show();
        }
    }

    public void controlPhone(View v) {
        if (mPhoneManager.getPhoneCallStatus() == PhoneManager.FLAG_DEFAULT) {
            mPhoneManager.controlPhoneCall(PhoneManager.FLAG_DISALLOW_OUT | PhoneManager.FLAG_DISALLOW_IN);
            Toast.makeText(this, "Disallow SIM0 in, SIM1 in out", Toast.LENGTH_SHORT).show();
        } else {
            mPhoneManager.controlPhoneCall(PhoneManager.FLAG_DEFAULT);
            Toast.makeText(this, "Control nothing", Toast.LENGTH_SHORT).show();
        }
    }

    public void controlCellular(View v) {
        if (mPhoneManager.getCellularStatus() == 0) {
            mPhoneManager.controlCellular(PhoneManager.DISABLE);
            Toast.makeText(this, "Disallow Cellular SIM0 & SIM1", Toast.LENGTH_SHORT).show();
        } else {
            mPhoneManager.controlCellular(PhoneManager.ENABLE);
            Toast.makeText(this, "Control nothing", Toast.LENGTH_SHORT).show();
        }
    }

    public void readIMEI(View v) {
        String imei1 = mPhoneManager.getIMEI(0);
        String imei2 = mPhoneManager.getIMEI(1);
        Toast.makeText(this, "IMEI1: " + imei1 + "\nIMEI2: " + imei2, Toast.LENGTH_SHORT).show();
    }

    public void callBlackList(View v) {
        List<String> blackList = new ArrayList<>();
        blackList.add("1380000000");
        PhoneManager.getInstance().setCallBlackList(blackList);
        if (PhoneManager.getInstance().getCallContactRestriction() != PhoneManager.RESTRICTION_MODE_DEFAULT) {
            PhoneManager.getInstance().setCallContactRestriction(PhoneManager.RESTRICTION_MODE_DEFAULT);
            Toast.makeText(this, "关闭黑名单", Toast.LENGTH_SHORT).show();
        } else {
            PhoneManager.getInstance().setCallContactRestriction(PhoneManager.RESTRICTION_MODE_BLACK_LIST);
            Toast.makeText(this, "开启黑名单", Toast.LENGTH_SHORT).show();
        }
    }

    public void callWhiteList(View v) {
        List<String> whiteList = new ArrayList<>();
        whiteList.add("1380000000");
        PhoneManager.getInstance().setCallWhiteList(whiteList);
        if (PhoneManager.getInstance().getCallContactRestriction() != PhoneManager.RESTRICTION_MODE_DEFAULT) {
            PhoneManager.getInstance().setCallContactRestriction(PhoneManager.RESTRICTION_MODE_DEFAULT);
            Toast.makeText(this, "关闭白名单", Toast.LENGTH_SHORT).show();
        } else {
            PhoneManager.getInstance().setCallContactRestriction(PhoneManager.RESTRICTION_MODE_WHITE_LIST);
            Toast.makeText(this, "开启白名单", Toast.LENGTH_SHORT).show();
        }
    }

    public void endCall(View v) {
        PhoneManager.getInstance().endCall();
    }

    public void disableCallForward(View v) {
        PhoneManager.getInstance().disableCallForward(true);
    }

    public void disableCallLog(View v) {
        PhoneManager.getInstance().disableCallLog(true);
    }

    public void getAreaCode(View v) {
        String areaCode = PhoneManager.getInstance().getAreaCode("1380000000");
        Toast.makeText(this, "1380000000 : " + areaCode, Toast.LENGTH_SHORT).show();
    }

    public void getMeid(View v) {
        String meid1 = PhoneManager.getInstance().getMeid(0);
        String meid2 = PhoneManager.getInstance().getMeid(1);
        Toast.makeText(this, "meid1 = " + meid1 + " ,meid2 = " + meid2,
                Toast.LENGTH_SHORT).show();
    }

    public void disableSim(View v) {
        PhoneManager.getInstance().setIccCardActivate(0, false);
    }

}
