package com.android.engineermode;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class EngineerModeApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		dbName = "engineermode.db";
		try {
			CommonUtil.copyFile(getAssets().open(dbName), "/data/data/com.android.engineermode/databases/"+dbName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sqLiteDatabase = new DBOpenHelper(this, dbName).open();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		sqLiteDatabase.close();
	}

	private String dbName;
	private SQLiteDatabase sqLiteDatabase;
	private WakeLock wakeLock;

	public String getDbName() {
		return dbName;
	}
	
	public SQLiteDatabase getSqLiteDatabase() {
		return sqLiteDatabase;
	}

	public WakeLock getWakeLock() {
		return wakeLock;
	}

	public void setWakeLock(WakeLock wakeLock) {
		this.wakeLock = wakeLock;
	}
	
}
