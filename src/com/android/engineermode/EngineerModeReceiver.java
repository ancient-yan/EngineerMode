package com.android.engineermode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;

public class EngineerModeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(action.equals("android.intent.action.PRESS_KEY_HOME")){
			EngineerModeApplication application = (EngineerModeApplication) context.getApplicationContext();
			WakeLock wakeLock = application.getWakeLock();
			if(wakeLock!=null&&wakeLock.isHeld()){
				wakeLock.release();
			}
		}
	}

}
