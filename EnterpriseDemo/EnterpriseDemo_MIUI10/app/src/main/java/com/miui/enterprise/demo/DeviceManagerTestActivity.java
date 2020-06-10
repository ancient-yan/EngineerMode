package com.miui.enterprise.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.miui.enterprise.sdk.DeviceManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DeviceManagerTestActivity extends AppCompatActivity {

    private DeviceManager mDeviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager_test);
        mDeviceManager = DeviceManager.getInstance();
    }

    public void getVersion(View v) {
        Toast.makeText(this, "API version: " + mDeviceManager.getAPIVersion(),
                Toast.LENGTH_SHORT).show();
    }

    public void isDeviceRoot(View v) {
        Toast.makeText(this, "Is device root: " + mDeviceManager.isDeviceRoot(),
                Toast.LENGTH_SHORT).show();
    }

    public void deviceShutDown(View v) {
        mDeviceManager.deviceShutDown();
    }

    public void deviceReboot(View v) {
        mDeviceManager.deviceReboot();
    }

    public void formatSdCard(View v) {
        mDeviceManager.formatSdCard();
    }

    public void captureScreen(View v) {
        Bitmap bitmap = mDeviceManager.captureScreen();
        if (bitmap != null) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String sdCardDir = Environment.getExternalStorageDirectory() + "/MIUI/";
                File dirFile = new File(sdCardDir);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                File file = new File(sdCardDir, System.currentTimeMillis() + ".jpg");

                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "save" + Environment.getExternalStorageDirectory() + "/MIUI/" + "目录文件夹下", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "captureScreen failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void recoveryFactory(View v) {
        mDeviceManager.recoveryFactory(false);
    }

    public void setBootAnimation(View view) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/MIUI/bootanimation.zip";
        mDeviceManager.setBootAnimation(path);
        Toast.makeText(this, "Set boot animation file: " + path, Toast.LENGTH_SHORT).show();
    }

}
