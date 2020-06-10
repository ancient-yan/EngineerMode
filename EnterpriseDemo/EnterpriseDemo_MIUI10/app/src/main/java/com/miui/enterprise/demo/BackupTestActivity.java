package com.miui.enterprise.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.miui.enterprise.sdk.ApplicationManager;

import java.io.File;

public class BackupTestActivity extends AppCompatActivity {

    private TextView mLogView;
    private StringBuilder mLogBuilder;
    private EditText mPackageText;
    //目标app data/data/{packageName}/目录下的文件。写“/”时备份整个目录，写“/files/”则备份app的files目录
    private static String mSrcFilePath = "/";
    //输出目录设置在sd卡的ent-backup
    private static String mOutDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/ent-backup/";
    private int mRequestId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_test);
        mLogView = findViewById(R.id.tv_log);
        mLogBuilder = new StringBuilder();
        mPackageText = findViewById(R.id.et_package);
        File outDir = new File(mOutDirPath);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        // 注册备份回调广播
        IntentFilter intentFilter = new IntentFilter(ApplicationManager.ACTION_BACKUP_FAILED);
        intentFilter.addAction(ApplicationManager.ACTION_BACKUP_FINISH);
        registerReceiver(mBackupReceiver, intentFilter);
        ApplicationManager.getInstance().setApplicationSettings(getPackageName(),
                ApplicationManager.FLAG_ALLOW_AUTOSTART | ApplicationManager.FLAG_KEEP_ALIVE | ApplicationManager.FLAG_GRANT_ALL_RUNTIME_PERMISSION, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBackupReceiver);
    }

    public void backup(View v) {
        switch (v.getId()) {
            case R.id.bt_backup:
                backupPackage(false);
                break;
            case R.id.bt_backup_dua:
                backupPackage(true);
                break;
        }
    }

    private BroadcastReceiver mBackupReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ApplicationManager.ACTION_BACKUP_FINISH)) {
                // 备份结束
                appendLog("backup for id: " + intent.getIntExtra(ApplicationManager.EXTRA_BACKUP_ID, -1)
                        + ", package:"
                        + intent.getStringExtra(Intent.EXTRA_PACKAGE_NAME) + " succeed.");
            } else if (intent.getAction().equals(ApplicationManager.ACTION_BACKUP_FAILED)) {
                // 备份失败
                appendLog("backup for id: " + intent.getIntExtra(ApplicationManager.EXTRA_BACKUP_ID, -1)
                        + ", package:"
                        + intent.getStringExtra(Intent.EXTRA_PACKAGE_NAME) + " failed.");
            }
        }
    };

    private void appendLog(final String logLine) {
        mLogBuilder.append(logLine).append("\n");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLogView.setText(logLine);
            }
        });
    }

    /**
     * 备份应用
     *
     * @param isDuoApp 是否是双开应用
     */
    private void backupPackage(boolean isDuoApp) {
        String packageName = mPackageText.getText().toString();
        if (TextUtils.isEmpty(packageName)) {
            appendLog("package name is empty.");
        } else if (isDuoApp && !ApplicationManager.getInstance().isAppInXSpace(packageName)) {
            //使用ApplicationManager.getInstance().isAppInXSpace判断应用是否被双开
            appendLog("there is no duo app for " + packageName);
        } else {
            //需要写绝对路径，双开应用的数据在"/data/user/999"下，底层以此为依据判断是否是双开
            String srcPath = (isDuoApp ? "/data/user/999/" : "/data/data/") + packageName + mSrcFilePath;
            // 每次备份以id为目录名在ent-backup下生成一个目录
            String outPath = mOutDirPath + (isDuoApp ? "duo" : "main") + "/" + mRequestId;
            //传入需要备份的数据的绝对路径，输出路径，需要备份的APP的包名
            ApplicationManager.getInstance().backupData(srcPath, outPath, packageName, mRequestId++);
            appendLog("start backup " + packageName + ", from " + srcPath + " to " + outPath);
        }
    }
}
