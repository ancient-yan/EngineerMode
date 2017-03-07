package com.android.engineermode;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Keystroke extends Activity {
	
	private TextView keyTestView;	
	private int autoKeyUpIndex = 0;
	private int autoKeyDownIndex = 0;
	private int autoKeyBackIndex = 0;
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keystroke);
		application = (EngineerModeApplication) getApplication();
		db = application.getSqLiteDatabase();
		id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
		if(id!=0){
			keyTestView = (TextView) findViewById(R.id.key_test);
			keyTestView.setText(R.string.auto_keystroke_up);
			final Button btnKeyBack = (Button) findViewById(R.id.btnkeyback);
			btnKeyBack.setText(R.string.keystroke_back);
			btnKeyBack.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					ContentValues values = new ContentValues();
    				values.put(AutoHardwareTest.ITEM, getString(R.string.keystroke));
    				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
    				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
    				CommonUtil.autoNextActivity(Keystroke.this, db, id+1);
					finish();
				}
			});
		}else{
			keyTestView = (TextView) findViewById(R.id.key_test);
			keyTestView.setText(R.string.keystroke_info);
			final Button btnKeyBack = (Button) findViewById(R.id.btnkeyback);
			btnKeyBack.setText(R.string.keystroke_back);
			btnKeyBack.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					finish();
				}
			});
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if(id!=0){
				if ((autoKeyUpIndex == 1) && (autoKeyDownIndex == 1) && (autoKeyBackIndex == 0)) {
					keyTestView = (TextView) findViewById(R.id.key_test);
					keyTestView.setText(R.string.auto_keystroke_menu);
					autoKeyBackIndex = 1;
				}
			}else{
				keyTestView = (TextView) findViewById(R.id.key_test);
				keyTestView.setText(R.string.back_key);
			}
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
			if(id!=0){
				if (autoKeyUpIndex == 0) {
					keyTestView = (TextView) findViewById(R.id.key_test);
					keyTestView.setText(R.string.auto_keystroke_down);
					autoKeyUpIndex = 1;
				}
			}else{
				keyTestView = (TextView) findViewById(R.id.key_test);
				keyTestView.setText(R.string.volume_up_key);
			}
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
			if(id!=0){
				if ((autoKeyUpIndex == 1) && (autoKeyDownIndex == 0)) {
					keyTestView = (TextView) findViewById(R.id.key_test);
					keyTestView.setText(R.string.auto_keystroke_back);
					autoKeyDownIndex = 1;
				}
			}else{
				keyTestView = (TextView) findViewById(R.id.key_test);
				keyTestView.setText(R.string.volume_down_key);
			}
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_HOME) {
			keyTestView = (TextView) findViewById(R.id.key_test);
			keyTestView.setText(R.string.home_key);
		}
		if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
			if(id!=0){
				if ((autoKeyUpIndex == 1) && (autoKeyDownIndex == 1)&& (autoKeyBackIndex == 1)) {
					ContentValues values = new ContentValues();
    				values.put(AutoHardwareTest.ITEM, getString(R.string.keystroke));
    				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
    				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
    				CommonUtil.autoNextActivity(Keystroke.this, db, id+1);
					finish();
				}
			}else{
				keyTestView = (TextView) findViewById(R.id.key_test);
				keyTestView.setText(R.string.menu_key);
			}
		}
		return true;
	}
}
