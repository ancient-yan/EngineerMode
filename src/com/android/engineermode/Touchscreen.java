package com.android.engineermode;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class Touchscreen extends Activity {
	private Context context;
	private TouchscreenView tsv;
	private Timer timer = new Timer();
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		tsv = new TouchscreenView(context);
		setContentView(tsv);
		application = (EngineerModeApplication) getApplication();
		db = application.getSqLiteDatabase();
		id = getIntent().getIntExtra(AutoHardwareTest._ID, 0);
		if(id!=0){
			timer.schedule(task, 3000);
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				timer.cancel();
				new CustomAlertDialog.Builder(Touchscreen.this)
        		.setTitle2(R.string.auto_test_choose,R.string.test_again,new View.OnClickListener() {
					public void onClick(View v) {
						CommonUtil.autoNextActivity(Touchscreen.this, db, id);
						finish();
					}
				}).setMessage(
						R.string.auto_touchscreen_res_info).setIcon(
						android.R.drawable.ic_dialog_info)
						.setPositiveButton(R.string.auto_test_no,new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								ContentValues values = new ContentValues();
	            				values.put(AutoHardwareTest.ITEM, getString(R.string.touchscreen));
	            				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
	            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
	            				CommonUtil.autoNextActivity(Touchscreen.this, db, id+1);
								finish();
							}
						}).setNegativeButton(R.string.auto_test_yes,new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								ContentValues values = new ContentValues();
	            				values.put(AutoHardwareTest.ITEM, getString(R.string.touchscreen));
	            				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
	            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
	            				CommonUtil.autoNextActivity(Touchscreen.this, db, id+1);
								finish();
							}
						}).show();
			
				break;
			}
		}
	};
	
	private TimerTask task = new TimerTask() {
		public void run() {
			Message msg = new Message();
			msg.what = 1;
			handler.sendMessage(msg);
		}
	};

}