package com.android.engineermode;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import android.util.Config;

public class HardwareTestRes extends Activity {
	
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private boolean testResult = true;
	
	private List<TextView> listTextView = new ArrayList<TextView>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.auto_hardware_res);
	    application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
		
	    listTextView.add((TextView) findViewById(R.id.textView1));
	    listTextView.add((TextView) findViewById(R.id.textView2));
	    listTextView.add((TextView) findViewById(R.id.textView3));
	    listTextView.add((TextView) findViewById(R.id.textView4));
	    listTextView.add((TextView) findViewById(R.id.textView5));
	    listTextView.add((TextView) findViewById(R.id.textView6));
	    listTextView.add((TextView) findViewById(R.id.textView7));
	    listTextView.add((TextView) findViewById(R.id.textView8));
	    listTextView.add((TextView) findViewById(R.id.textView9));
	    listTextView.add((TextView) findViewById(R.id.textView10));
	    listTextView.add((TextView) findViewById(R.id.textView11));
	    listTextView.add((TextView) findViewById(R.id.textView12));
	    listTextView.add((TextView) findViewById(R.id.textView13));
		
	    Cursor cursor = db.query(AutoHardwareTest.TABLE_NAME, null, null, null, null, null, AutoHardwareTest._ID);
	    for(TextView textView:listTextView){
	    	if(cursor.moveToNext()){
	    		String item = cursor.getString(cursor.getColumnIndex(AutoHardwareTest.ITEM));
	    		int result = cursor.getInt(cursor.getColumnIndex(AutoHardwareTest.RESULT));
	    		if(result==AutoHardwareTest.RESULT_OK){
	    			textView.setText(item+getString(R.string.auto_hardware_res_is_ok));
	    			textView.setTextColor(android.graphics.Color.BLUE);
	    		}else if(result==AutoHardwareTest.RESULT_FAILD){
	    			testResult = false;
	    			textView.setText(item+getString(R.string.auto_hardware_res_is_not_ok));
	    			textView.setTextColor(android.graphics.Color.RED);
	    		}else{
	    			textView.setText(item+getString(R.string.auto_hardware_res_not_test));
	    			textView.setTextColor(android.graphics.Color.YELLOW);
	    		}
	    	}
	    }
	    cursor.close();	

	}
	
	private void writeResult() {
	}

}
