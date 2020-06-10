package com.miui.enterprise.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.miui.enterprise.sdk.APNConfig;
import com.miui.enterprise.sdk.APNManager;

import java.util.Date;
import java.util.List;

public class APNTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apntest);
    }

    public void addApn(View view) {
        APNConfig apnConfig = new APNConfig();
        apnConfig.mName = "APN1";
        apnConfig.mApn = "test";
        apnConfig.mUser = "test";
        apnConfig.mPassword = "test";
        APNManager.getInstance().addAPN(apnConfig);
        Toast.makeText(this, "Add APN:" + apnConfig, Toast.LENGTH_SHORT).show();
    }

    public void editAPN(View v) {
        String apnName = "APN1";
        APNConfig apnConfig = APNManager.getInstance().getAPN(apnName);
        if (apnConfig == null) {
            Toast.makeText(this, "Not found " + apnName, Toast.LENGTH_SHORT).show();
        } else {
            apnConfig.mUser = "test1";
            apnConfig.mPassword = "test1";
            apnConfig.mApn = new Date().toString();
            APNManager.getInstance().editAPN(apnName, apnConfig);
        }
        apnConfig = APNManager.getInstance().getAPN(apnName);
        Toast.makeText(this, "New APN:" + apnConfig, Toast.LENGTH_SHORT).show();
    }

    public void getAPN(View v) {
        List<APNConfig> apnConfigs = APNManager.getInstance().getAPNList();
        StringBuilder sb = new StringBuilder();
        for (APNConfig config : apnConfigs) {
            sb.append(config.toString()).append("\n");
        }
        Toast.makeText(this, "APN List:\n" + sb.toString(), Toast.LENGTH_SHORT).show();
    }

    public void deleteAPN(View v) {
        String apnName = "APN1";
        APNManager.getInstance().deleteAPN(apnName);
        APNConfig config = APNManager.getInstance().getAPN(apnName);
        if (config == null) {
            Toast.makeText(this, "Delete success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
        }
    }

}
