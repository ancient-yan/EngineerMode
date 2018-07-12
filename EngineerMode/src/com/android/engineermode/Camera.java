package com.android.engineermode;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class Camera extends Activity {
	
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
	    id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
	    if(!CommonUtil.hasSd()){
	    	ContentValues values = new ContentValues();
			values.put(AutoHardwareTest.ITEM, getString(R.string.auto_camera_res_title));
			values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_UNKONW);
			db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
			CommonUtil.autoNextActivity(Camera.this, db, id+1);
			finish();
			return;
		}
	    ComponentName comp = new ComponentName("com.android.camera", "com.android.camera.Camera");
	    Intent mIntent = new Intent();
		mIntent.setComponent(comp);
		mIntent.setAction("android.intent.action.VIEW");
		startActivityForResult(mIntent,0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		new CustomAlertDialog.Builder(Camera.this)
		.setTitle2(R.string.auto_test_choose,R.string.test_again,new View.OnClickListener() {
			public void onClick(View v) {
				CommonUtil.autoNextActivity(Camera.this, db, id);
				finish();
			}
		}).setMessage(
				R.string.auto_camera_res_info).setIcon(
				android.R.drawable.ic_dialog_info)
				.setPositiveButton(R.string.auto_test_no,new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int which) {
						ContentValues values = new ContentValues();
        				values.put(AutoHardwareTest.ITEM, getString(R.string.auto_camera_res_title));
        				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
        				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
        				CommonUtil.autoNextActivity(Camera.this, db, id+1);
						finish();
					}
				}).setNegativeButton(R.string.auto_test_yes,new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int which) {
						ContentValues values = new ContentValues();
        				values.put(AutoHardwareTest.ITEM, getString(R.string.auto_camera_res_title));
        				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
        				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
        				CommonUtil.autoNextActivity(Camera.this, db, id+1);
						finish();
					}
				}).show();
	}
}
