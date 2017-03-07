package com.android.engineermode;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class AutoTest extends Activity {
	
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
	    String sqlClear = "update "+AutoHardwareTest.TABLE_NAME+" set "+AutoHardwareTest.ITEM+"=null,"+AutoHardwareTest.RESULT+"=null";
	    db.execSQL(sqlClear);
	    CommonUtil.autoNextActivity(this, db, 1);
	    finish();
	}
	
}
