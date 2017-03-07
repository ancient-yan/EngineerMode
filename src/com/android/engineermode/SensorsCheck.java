package com.android.engineermode;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SensorsCheck extends Activity implements SensorEventListener {
	private TextView Sensor2View;
	private SensorManager sm = null;
	private TextView xViewA = null;
	private TextView yViewA = null;
	private TextView zViewA = null;
	private int xTestRes = 0;
	private 	int yTestRes = 0;
	private int zTestRes = 0;
	private int xyzTestRes = 1;
	private Timer timer;
	private Button btnSensor3;
	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
	    id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
	    sm = (SensorManager) getSystemService(SENSOR_SERVICE);
	    setContentView(R.layout.sensors_check);
	    Sensor2View =(TextView)findViewById(R.id.Sensor2_test);   
	    xViewA =(TextView)findViewById(R.id.x_info);
	    yViewA =(TextView)findViewById(R.id.y_info);
	    zViewA =(TextView)findViewById(R.id.z_info);
		if (id!=0) {
			Sensor2View.setText(R.string.auto_sensorscheck_title);
        	xViewA.setText("Accel X: " + getString(R.string.auto_sensorscheck_unknown));
        	yViewA.setText("Accel Y: " + getString(R.string.auto_sensorscheck_unknown));
        	zViewA.setText("Accel Z: " + getString(R.string.auto_sensorscheck_unknown));
			final Button btnSensor2 = (Button) findViewById(R.id.btnSensor2);
			btnSensor2.setText(R.string.auto_sensorscheck_info);
			btnSensor2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					ContentValues contentValues = new ContentValues();
					contentValues.put(AutoHardwareTest.ITEM, getString(R.string.sensorscheck));
					contentValues.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
    				db.update(AutoHardwareTest.TABLE_NAME, contentValues, AutoHardwareTest._ID+" = "+id, null);
    				CommonUtil.autoNextActivity(SensorsCheck.this, db, id+1);
					finish();
				}
			});
	    }else{
	    	Sensor2View.setText(R.string.Sensor2_info);
			btnSensor3 = (Button) findViewById(R.id.btnSensor2);
			btnSensor3.setText(R.string.Sensor2_start);
			btnSensor3.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					try {
						Runtime.getRuntime().exec("calibratebma");
						btnSensor3.setEnabled(false);
						timer = new Timer();
					    TimerTask task = new TimerTask(){   
					        public void run() {   
					            Message message = new Message();       
					            message.what = 1;       
					            handler.sendMessage(message);     
					        }       
					    }; 
						timer.schedule(task, 3000, 6000); 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	    }
	}
	
	Handler handler = new Handler(){   
		  
        public void handleMessage(Message msg) {   
            switch (msg.what) {
            case 1:
            	timer.cancel();
            	btnSensor3.setEnabled(true);
            	showSensorsCheckRes();
                break;
            }
            super.handleMessage(msg);
        }
    };
    
    public void showSensorsCheckRes(){
    	Toast.makeText(this, getString(R.string.Sensor2_res_info), Toast.LENGTH_LONG).show();
    }
	
    @Override
    protected void onResume() {
        super.onResume();
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    @Override
    protected void onStop() {
        sm.unregisterListener(this);
        super.onStop();
    }

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
	    Log.e("SensorsCheck",  "sensor: " + event.sensor.getType() + ", x: " + event.values[0] + ", y: " + event.values[1] + ", z: " + event.values[2]);
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
	        synchronized (this) {
            	if (id!=0){
            		if(values[0]>6){
            			xViewA.setText("Accel X: " + getString(R.string.auto_sensorscheck_yes));
						xTestRes = 1;
            		}
            		if(values[1]>6){
            			yViewA.setText("Accel Y: " + getString(R.string.auto_sensorscheck_yes));
						yTestRes = 1;
            		}
            		if(values[2]>6){
            			zViewA.setText("Accel Z: " + getString(R.string.auto_sensorscheck_yes));
						zTestRes = 1;
            		}
					if ((xTestRes == 1) && (yTestRes == 1) && (zTestRes == 1)&&(xyzTestRes == 1)) {
						ContentValues contentValues = new ContentValues();
						contentValues.put(AutoHardwareTest.ITEM, getString(R.string.sensorscheck));
						contentValues.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
        				db.update(AutoHardwareTest.TABLE_NAME, contentValues, AutoHardwareTest._ID+" = "+id, null);
        				CommonUtil.autoNextActivity(SensorsCheck.this, db, id+1);
						xyzTestRes = 0;
						finish();
					}
            	}else{
                	xViewA.setText("Accel X: " + values[0]);
                	yViewA.setText("Accel Y: " + values[1]);
                	zViewA.setText("Accel Z: " + values[2]);
            	}
	        }
		}
	}   
}
