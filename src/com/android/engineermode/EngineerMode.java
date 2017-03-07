package com.android.engineermode;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class EngineerMode extends ListActivity {
	
	private static final int GPS_TEST = 0;
	private static final int WIFI_TEST = 1;
	private static final int BT_TEST = 2;
	
	private String[] mStrings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mStrings =new String[11];
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
		
        int index = 0;
        mStrings[index++] = getString(R.string.phone_info);
        mStrings[index++] = getString(R.string.hardware_test);
//        mStrings[index++] = getString(R.string.touchscreencheck);
        mStrings[index++] = getString(R.string.sensorscheck);
        mStrings[index++] = getString(R.string.touchscreen_test);
        mStrings[index++] = getString(R.string.camera_test);
        mStrings[index++] = getString(R.string.gps_test);
        mStrings[index++] = getString(R.string.wifi_test);
        mStrings[index++] = getString(R.string.bluetooth_test);
        mStrings[index++] = getString(R.string.stress_test);
        mStrings[index++] = getString(R.string.verify_info);
        mStrings[index++] = getString(R.string.battary_info);
    }
    
    @Override
	protected void onResume() {
		EngineerModeApplication application = (EngineerModeApplication) getApplication();
		WakeLock wakeLock = application.getWakeLock();
		if(wakeLock==null){
			PowerManager powerManager = (PowerManager)getSystemService(POWER_SERVICE);
			wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK |PowerManager.ON_AFTER_RELEASE, "EngineerMode");
			application.setWakeLock(wakeLock);
		}
		if(!wakeLock.isHeld()){
			wakeLock.acquire();
		}
		super.onResume();
	}
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
		EngineerModeApplication application = (EngineerModeApplication) getApplication();
		WakeLock wakeLock = application.getWakeLock();
		if(wakeLock!=null&&wakeLock.isHeld()){
			wakeLock.release();
		}
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent intent = null;
    	switch(position){    		
    	case 1:		
    		intent = new Intent(this, HardwareTest.class);
    		break;

//    	case 2:		
//    		intent = new Intent(this,TouchscreenCheck.class);
//    		break;
    	case 2:		
    		intent = new Intent(this, SensorsCheck.class);
    		break;
    	case 3:		
    		intent = new Intent(this,Touchscreen.class);
    		break;
    	case 4:
    		intent = new Intent();
			intent.setComponent(new ComponentName("com.android.camera", "com.android.camera.Camera"));
			intent.setAction("android.intent.action.VIEW");
    		break;
    		
    	case 5:		
			try {
				intent = new Intent();
				intent.setClassName("com.chartcross.gpstest",
						"com.chartcross.gpstest.GPSTest");
				startActivityForResult(intent, GPS_TEST);
			} catch (Exception e) {
				Log.e("###", e.toString());
			} finally {
				return;
			}
    	case 6:
    		intent = new Intent();
			intent.setClassName("com.android.settings",
					"com.android.settings.wifi.WifiSettings");
    	try {
				startActivityForResult(intent, WIFI_TEST);
			} catch (Exception e) {
				Log.e("###", e.toString());
			} finally {
				return;
			}
    	case 7:
    		intent = new Intent();
    		intent.setAction(Intent.ACTION_MAIN);
			intent.setClassName("com.android.settings",
					"com.android.settings.bluetooth.BluetoothSettings");
    		try {
				startActivityForResult(intent, BT_TEST);
			} catch (Exception e) {
				Log.e("###", e.toString());
			} finally {
				return;
			}
       	case 8:		
			try {
				intent = new Intent();
				ComponentName comp = new ComponentName("com.android",
						"com.android.AutoTest");
				intent.setComponent(comp);

				break;
			} catch (Exception e) {
				Log.e("###",e.toString());
			}
       	case 9:
       		intent = new Intent(this, VerifyInfo.class);
       		break;
       	case 10:
       		intent = new Intent(this, BatteryInfo.class);
       		break;
    	}
    	
    	if(intent != null){
			try{
    		   startActivity(intent);
    		}catch(Exception e){
    		   Log.e("###",e.toString());
    		}
    	}
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	int msgId = 0;
    	int code = 0;
    	switch (requestCode) {
    		case GPS_TEST:
    		msgId = R.string.is_gps_test_ok;
    		code = GPS_TEST;
    		break;
    		case WIFI_TEST:
    		code = WIFI_TEST;
    		msgId = R.string.is_wifi_test_ok;
    		break;
    		case BT_TEST:
    		code = BT_TEST;
    		msgId = R.string.is_bt_test_ok;
    		break;
    		default:
    	}
    	final int itemCode = code;
    	new AlertDialog.Builder(this)
					.setTitle(R.string.auto_test_choose).setMessage(msgId)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton(R.string.result_fail, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
									writeResult(itemCode, 1);
								}
							}).setNegativeButton(R.string.result_pass, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							writeResult(itemCode, 3);
						}
					}).create().show();
    }
    
    private void writeResult(int itemCode, int result) {
    	int index = 0;
    	switch (itemCode) {
    		case GPS_TEST:
    		index = 25;
    		break;
    		case WIFI_TEST:
    		index = 26;
    		break;
    		case BT_TEST:
    		index = 27;
    		break;
    		default:
    	}
	}
}

