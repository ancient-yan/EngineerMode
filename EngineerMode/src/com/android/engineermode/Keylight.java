package com.android.engineermode;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Keylight extends Activity {
    private final int ID_LED=20110915;
	private NotificationManager nm;
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;

	@Override
	protected void onPause() {
		super.onPause();
		nm.cancel(ID_LED);	
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.indicator);
	    application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
	    id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
	    nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Button btnLightOn = (Button) findViewById(R.id.btnLightOn);
		btnLightOn.setText(R.string.indicator_on);
		btnLightOn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				nm.cancel(ID_LED);
		        Notification notification = new Notification();
		        notification.ledARGB = 0x66778899;  //0xFF0000 is red
		        notification.ledOnMS = 100;
		        notification.ledOffMS = 0;		
		        notification.flags = Notification.FLAG_SHOW_LIGHTS;
		        nm.notify(ID_LED, notification);
			}
		});
		
		Button btnLightOff = (Button) findViewById(R.id.btnLightOff);
		btnLightOff.setText(R.string.indicator_off);
		btnLightOff.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				nm.cancel(ID_LED);
			}
		});
		
		if (id!=0) {
			nm.cancel(ID_LED);
	        Notification notification = new Notification();
	        notification.ledARGB = 0x66778899;  //0xFF0000 is red
	        notification.ledOnMS = 100;
	        notification.ledOffMS = 0;		
	        notification.flags = Notification.FLAG_SHOW_LIGHTS;
	        nm.notify(ID_LED, notification);
	        new CustomAlertDialog.Builder(Keylight.this)
    		.setTitle2(R.string.auto_test_choose,R.string.test_again,new View.OnClickListener() {
				public void onClick(View v) {
					CommonUtil.autoNextActivity(Keylight.this, db, id);
					finish();
				}
			}).setMessage(R.string.auto_keylight_title)
			.setIcon(android.R.drawable.ic_dialog_info)
			.setPositiveButton(R.string.auto_test_no, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int which) {
					ContentValues values = new ContentValues();
    				values.put(AutoHardwareTest.ITEM, getString(R.string.keylight));
    				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
    				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
    				CommonUtil.autoNextActivity(Keylight.this, db, id+1);
					nm.cancel(ID_LED);
					finish();
				}
			}).setNegativeButton(R.string.auto_test_yes, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					ContentValues values = new ContentValues();
    				values.put(AutoHardwareTest.ITEM, getString(R.string.keylight));
    				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
    				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
    				CommonUtil.autoNextActivity(Keylight.this, db, id+1);
					nm.cancel(ID_LED);
					finish();
				}
			}).show();
		}
	}

}
