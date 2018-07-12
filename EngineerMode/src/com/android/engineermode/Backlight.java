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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Backlight extends Activity {

	private SeekBar sBar;
	private TextView lightUp;
	private TextView lightDown;
	private Timer timer = new Timer();
	private int sBarIndex=20;
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.backlight);
		application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
	    id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
		sBar = (SeekBar) findViewById(R.id.seekb);
		lightUp= (TextView) findViewById(R.id.lightup);
		lightDown= (TextView) findViewById(R.id.lightdown);
		lightUp.setText(R.string.backlight_up);
		lightDown.setText(R.string.backlight_down);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.screenBrightness = 0.5f;
		getWindow().setAttributes(lp);
		sBar.setProgress(50);
		sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				float index = progress;
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.screenBrightness = index / 100;
				Log.e("---------", String.valueOf(index / 100));
				if(index>10.0){
				    getWindow().setAttributes(lp);
				}
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});
		
		if (id!=0) {
			timer.schedule(task, 1000, 1000);
		}

	}
	
    Handler handler = new Handler(){   
		  
        public void handleMessage(Message msg) {   
            switch (msg.what) {       
            case 1:
            	sBar.setProgress(sBarIndex);
				sBarIndex += 20;
                break;    
            case 2:
            	timer.cancel();
				try {
					new CustomAlertDialog.Builder(Backlight.this)
            		.setTitle2(R.string.auto_test_choose,R.string.test_again,new View.OnClickListener() {
						public void onClick(View v) {
							CommonUtil.autoNextActivity(Backlight.this, db, id);
							finish();
						}
					}).setMessage(
							R.string.auto_backlight_res_info).setIcon(
							android.R.drawable.ic_dialog_info)
							.setPositiveButton(R.string.auto_test_no,new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {
									ContentValues values = new ContentValues();
		            				values.put(AutoHardwareTest.ITEM, getString(R.string.backlight));
		            				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
		            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
		            				CommonUtil.autoNextActivity(Backlight.this, db, id+1);
									finish();
								}
							}).setNegativeButton(R.string.auto_test_yes,new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {
									ContentValues values = new ContentValues();
		            				values.put(AutoHardwareTest.ITEM, getString(R.string.backlight));
		            				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
		            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
		            				CommonUtil.autoNextActivity(Backlight.this, db, id+1);
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
			if (sBarIndex < 100) {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
			else{
				Message message = new Message();
				message.what = 2;
				handler.sendMessage(message);
			}
        }          
    }; 

}
