package com.android.engineermode;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class Vibrate extends Activity {
	
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.vibrate);
	    application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
	    id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		long[] pattern = { 100, 10, 100, 5000 };
		vibrator.vibrate(pattern, -1);
		try {
			new CustomAlertDialog.Builder(Vibrate.this)
    		.setTitle2(R.string.auto_test_choose,R.string.test_again,new View.OnClickListener() {
				public void onClick(View v) {
					CommonUtil.autoNextActivity(Vibrate.this, db, id);
					finish();
				}
			}).setMessage(
					R.string.auto_vibrate_res_info).setIcon(
					android.R.drawable.ic_dialog_info)
					.setPositiveButton(R.string.auto_test_no,new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int which) {
							ContentValues values = new ContentValues();
            				values.put(AutoHardwareTest.ITEM, getString(R.string.vibrate));
            				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
            				CommonUtil.autoNextActivity(Vibrate.this, db, id+1);
							finish();
						}}
					).setNegativeButton(R.string.auto_test_yes,new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int which) {
							ContentValues values = new ContentValues();
            				values.put(AutoHardwareTest.ITEM, getString(R.string.vibrate));
            				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
            				CommonUtil.autoNextActivity(Vibrate.this, db, id+1);
							finish();
						}
					}).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
