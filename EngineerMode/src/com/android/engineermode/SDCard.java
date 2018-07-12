package com.android.engineermode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SDCard extends Activity {
	
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;
	private boolean flag;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sdcard);
		application = (EngineerModeApplication) getApplication();
		db = application.getSqLiteDatabase();
		id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
		String strRead = null;
		String str = "this is a test about Write SD card file";
		try {
			FileWriter fw = new FileWriter("/sdcard/cc.txt");
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			Log.e("testSD-write:", e.toString());
		}
		try {

			FileInputStream fileIS = new FileInputStream("/sdcard/cc.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(
					fileIS));
			String readString = new String();
			// just reading each line and pass it on the debugger
			while ((readString = buf.readLine()) != null) {
				strRead = readString;
				Log.e("testSD-read:", readString);
			}
			fileIS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String message;
		if (str.equals(strRead)) {
			Log.e("autoTestSD:", "OK~");
			message =  getString(R.string.hw_test_sd_ok);
			flag =true;
		} else {
			Log.e("autoTestSD:", "Error~");
			message =  getString(R.string.hw_test_sd_error);
			flag = false;
		}
		
		new CustomAlertDialog.Builder(SDCard.this)
		.setTitle2(R.string.auto_test_choose,R.string.test_again,new View.OnClickListener() {
			public void onClick(View v) {
				CommonUtil.autoNextActivity(SDCard.this, db, id);
				finish();
			}
		}).setMessage(message)
		.setIcon(android.R.drawable.ic_dialog_info)
		.setPositiveButton(R.string.auto_test_yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				dialog.dismiss();
				ContentValues values = new ContentValues();
				values.put(AutoHardwareTest.ITEM, getString(R.string.sdcard));
				if(flag){
					values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
				}else{
					values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
				}
				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
				CommonUtil.autoNextActivity(SDCard.this, db, id+1);
				finish();
			}
		}).show();
	}

}
