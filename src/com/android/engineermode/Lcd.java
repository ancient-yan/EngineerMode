package com.android.engineermode;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Lcd extends Activity {
	
	private LcdView lcd = null;
	private Timer timer = new Timer();
	private Timer timer2 = new Timer();
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;
	
	Handler handler = new Handler(){   
		  
        public void handleMessage(Message msg) {   
            switch (msg.what) {       
            case 1:
            	lcd.invalidate();
                break;    
            case 2:
            	finish();
            	break; 
            case 3:
            	timer2.cancel();
				try {
					new CustomAlertDialog.Builder(Lcd.this)
            		.setTitle2(R.string.auto_test_choose,R.string.test_again,new View.OnClickListener() {
						public void onClick(View v) {
							CommonUtil.autoNextActivity(Lcd.this, db, id);
							finish();
						}
					}).setMessage(
							R.string.auto_lcd_title).setIcon(
							android.R.drawable.ic_dialog_info)
							.setPositiveButton(R.string.auto_test_no,new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {
									ContentValues values = new ContentValues();
		            				values.put(AutoHardwareTest.ITEM, getString(R.string.lcd));
		            				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
		            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
		            				CommonUtil.autoNextActivity(Lcd.this, db, id+1);
									finish();
								}
							}).setNegativeButton(R.string.auto_test_yes,new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {
									ContentValues values = new ContentValues();
		            				values.put(AutoHardwareTest.ITEM, getString(R.string.lcd));
		            				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
		            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
		            				CommonUtil.autoNextActivity(Lcd.this, db, id+1);
									finish();
								}
							}).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
                break;  
            }       
            super.handleMessage(msg);   
        }   
    };
    
    TimerTask task = new TimerTask(){   
  	  
        public void run() {   
            Message message = new Message();       
            message.what = 1;       
            handler.sendMessage(message);     
        }   
    };  
    
	TimerTask task2 = new TimerTask() {

		public void run() {
			Message message = new Message();
			if (LcdView.isStop > 4) {
				message.what = 2;
				handler.sendMessage(message);
			}
		}
	};
    
    TimerTask task3 = new TimerTask(){   
  	  
		public void run() {
			Message message = new Message();
			if (LcdView.isStop == 5) {
				message.what = 3;
				handler.sendMessage(message);
			}
		}
    };  
     

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
	    id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window win = getWindow();
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (lcd == null)
    		lcd = new LcdView(this);
	    setContentView(lcd);    
	    timer.schedule(task, 1000, 1000); 
	    if(id!=0){
	    	timer2.schedule(task3, 0, 1000); 
	    }else {
	    	timer2.schedule(task2, 0, 1000); 
	    }
	}
}
