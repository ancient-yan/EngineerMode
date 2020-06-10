package com.miui.enterprise.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAPNManagerTest(View v) {
        startActivity(new Intent(this, APNTestActivity.class));
    }

    public void startRestrictionsManagerTest(View v) {
        startActivity(new Intent(this, RestrictionsManagerTestActivity.class));
    }

    public void startDeviceManagerSerTest(View v) {
        startActivity(new Intent(this, DeviceManagerTestActivity.class));
    }

    public void startApplicationManagerTest(View v) {
        startActivity(new Intent(this, ApplicationManagerTestActivity.class));
    }

    public void startPhoneManagerTest(View v) {
        startActivity(new Intent(this, PhoneManagerTestActivity.class));
    }

    public void startBackupTest(View v) {
        startActivity(new Intent(this, BackupTestActivity.class));
    }

}
