package com.miui.enterprise.demo;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageInstallObserver2;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.miui.enterprise.sdk.ApplicationManager;

import java.util.List;

public class ApplicationManagerTestActivity extends AppCompatActivity {

    private static final String TAG = "Enterprise:App";

    private ApplicationManager mApplicationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_manager_test);
        mApplicationManager = ApplicationManager.getInstance();
    }

    private class MyPackageInstallObserver extends IPackageInstallObserver2.Stub {
        @Override
        public void onUserActionRequired(Intent intent) {
            // Ignore
        }

        /**
         * 在Android P 上此接口之返回 PackageName 和 returnCode，并且会在异步线程中返回
         */
        @Override
        public void onPackageInstalled(String basePackageName, int returnCode, String msg, Bundle extras) {
            final String packageName = basePackageName;
            final int code = returnCode;
            final String msg1 = msg;
            ApplicationManagerTestActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "Install package:" + packageName + ", returnCode:" + code + ", msg:" + msg1);
                    Toast.makeText(ApplicationManagerTestActivity.this,
                            "Install " + packageName + " returned " + code, Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private class MyPackageDeleteObserver extends IPackageDeleteObserver.Stub {
        @Override
        public void packageDeleted(String packageName, int returnCode) {
            Log.d(TAG, "Delete package:" + packageName + ", returnCode:" + returnCode);
            Toast.makeText(ApplicationManagerTestActivity.this,
                    "Delete " + packageName + " succeed", Toast.LENGTH_SHORT).show();
        }
    }

    public void installPackage(View v) {
        mApplicationManager.installPackage("/sdcard/test.apk", 2 /* INSTALL_REPLACE_EXISTING */,
                new MyPackageInstallObserver());
    }

    public void deletePackage(View v) {
        mApplicationManager.deletePackage("com.miui.enterprise.demo", 4 /* DELETE_SYSTEM_APP */,
                new MyPackageDeleteObserver());
    }

    public void installPackageWithPendingIntent(View v) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, TestService.class));
        PendingIntent pIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ApplicationManager.getInstance().installPackageWithPendingIntent("/sdcard/test.apk", pIntent, 0);
    }

    public void setApplicationSettings(View v) {
        int flag = mApplicationManager.getApplicationSettings(getPackageName());
        if ((flag & ApplicationManager.FLAG_KEEP_ALIVE) != 0) {
            flag = ApplicationManager.FLAG_DEFAULT;
        } else {
            flag = ApplicationManager.FLAG_KEEP_ALIVE |
                    ApplicationManager.FLAG_PREVENT_UNINSTALLATION |
                    ApplicationManager.FLAG_GRANT_ALL_RUNTIME_PERMISSION |
                    ApplicationManager.FLAG_ALLOW_AUTOSTART;
        }
        mApplicationManager.setApplicationSettings(getPackageName(), flag);
        mApplicationManager.setApplicationSettings("com.miui.enterprise.demo", ApplicationManager.FLAG_DEFAULT);
        Toast.makeText(this,
                "set application setting for pkg[" + getPackageName() + "]" + ", flag=" + flag,
                Toast.LENGTH_SHORT).show();
    }

    public void setBlackList(View v) {
        List<String> blackList = mApplicationManager.getApplicationBlackList();
        if (blackList.isEmpty()) {
            blackList.add("com.sina.weibo");
            blackList.add("com.tencent.mm");
        } else {
            blackList.clear();
        }
        mApplicationManager.setApplicationBlackList(blackList);
        mApplicationManager.setApplicationRestriction(blackList.isEmpty() ?
                ApplicationManager.RESTRICTION_MODE_DEFAULT : ApplicationManager.RESTRICTION_MODE_BLACK_LIST);
        StringBuilder sb = new StringBuilder();
        for (String pkg : blackList) {
            sb.append(pkg).append("\n");
        }
        Toast.makeText(this, "set app black list: " + sb.toString(), Toast.LENGTH_SHORT).show();
    }

    public void setWhiteList(View v) {
        List<String> whiteList = mApplicationManager.getApplicationWhiteList();
        if (whiteList.isEmpty()) {
            whiteList.add("com.tencent.mm");
        } else {
            whiteList.clear();
        }
        mApplicationManager.setApplicationWhiteList(whiteList);
        mApplicationManager.setApplicationRestriction(whiteList.isEmpty() ?
                ApplicationManager.RESTRICTION_MODE_DEFAULT : ApplicationManager.RESTRICTION_MODE_WHITE_LIST);
        StringBuilder sb = new StringBuilder();
        for (String pkg : whiteList) {
            sb.append(pkg).append("\n");
        }
        Toast.makeText(this, "set app white list: " + sb.toString(), Toast.LENGTH_SHORT).show();
    }

    public void disallowRunning(View v) {
        List<String> list = mApplicationManager.getDisallowedRunningAppList();
        if (list.isEmpty()) {
            list.add("com.android.camera");
        } else {
            list.clear();
        }
        mApplicationManager.setDisallowedRunningAppList(list);
        StringBuilder sb = new StringBuilder();
        for (String pkg : list) {
            sb.append(pkg).append("\n");
        }
        Toast.makeText(this, "set app white list: " + sb.toString(), Toast.LENGTH_SHORT).show();
    }

}
