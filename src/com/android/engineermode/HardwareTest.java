package com.android.engineermode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Config;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.PowerManager;
import android.content.Context;

public class HardwareTest extends Activity {
	static Boolean bIsStart = false;
	Vibrator vibrator = null;
	private PowerManager.WakeLock wl;

	private void releaseWakeLock() {
		try {
			wl.release();
		} catch (Exception e) {
		}
	}
	
	private void acquireWakeLock() {
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "HardwareTest");
		wl.acquire();
	}

	@Override
	protected void onPause() {
		super.onPause();

		if(EngCofig.bIsVibrateTest){
			if(bIsStart){
				if(null != vibrator){
					vibrator.cancel();
					releaseWakeLock();
				}

				bIsStart = !bIsStart;
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO Auto-generated method stub
		setContentView(R.layout.hwtest);

		final Button btnAutoTest = (Button) findViewById(R.id.btnAutoTest);
		btnAutoTest.setText(R.string.autotest);
		btnAutoTest.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, AutoTest.class);
				startActivity(in);
			}
		});

		final Button btnLcd = (Button) findViewById(R.id.btnLcd);
		btnLcd.setText(R.string.lcd);

		btnLcd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, Lcd.class);
				startActivity(in);
			}
		});

		final Button btnVibrate = (Button) findViewById(R.id.btnVibrate);
		btnVibrate.setText(R.string.vibrate);

		btnVibrate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
				long[] pattern = { 100, 10, 100, 5000 };
				if(EngCofig.bIsVibrateTest){					
					if(!bIsStart){
						vibrator.vibrate(pattern, 0);
						acquireWakeLock();
					}
					else{
						vibrator.cancel();
						releaseWakeLock();
					}

					bIsStart = !bIsStart;
				}
				else{					
					vibrator.vibrate(pattern, -1);
				}
			}
		});

		final Button btnSDcard = (Button) findViewById(R.id.btnSDcard);
		btnSDcard.setText(R.string.sdcard);

		btnSDcard.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
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

					FileInputStream fileIS = new FileInputStream(
							"/sdcard/cc.txt");
					BufferedReader buf = new BufferedReader(
							new InputStreamReader(fileIS));
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

				if (str.equals(strRead)) {
					new AlertDialog.Builder(HardwareTest.this).setTitle(
							getString(R.string.hw_test_ok)).setMessage(
							getString(R.string.hw_test_sd_ok))
							.setPositiveButton(getString(R.string.hw_test_yes),
									null).show();
				} else {
					new AlertDialog.Builder(HardwareTest.this).setTitle(
							getString(R.string.hw_test_error)).setMessage(
							getString(R.string.hw_test_sd_error))
							.setPositiveButton(getString(R.string.hw_test_yes),
									null).show();
				}
			}
		});
		
		final Button btnKeystroke = (Button) findViewById(R.id.btnKeystroke);
		btnKeystroke.setText(R.string.keystroke);

		btnKeystroke.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, Keystroke.class);
				startActivity(in);
			}
		});
		
		final Button btnBacklight = (Button) findViewById(R.id.btnBacklight);
		btnBacklight.setText(R.string.backlight);

		btnBacklight.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, Backlight.class);
				startActivity(in);
			}
		});
		
		final Button btnAudio = (Button) findViewById(R.id.btnAudio);
		btnAudio.setText(R.string.audio);

		btnAudio.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, Audio.class);
				startActivity(in);
			}
		});
		
		final Button btnTouchScreen = (Button) findViewById(R.id.btnTouchScreen);
		btnTouchScreen.setText(R.string.touchscreen);

		btnTouchScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, Touchscreen.class);
				startActivity(in);
			}
		});
		
		final Button btnIndicator = (Button) findViewById(R.id.btnIndicator);
		btnIndicator.setText(R.string.indicator);

		btnIndicator.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, Indicator.class);
				startActivity(in);
			}
		});
		
		final Button btnSensors = (Button) findViewById(R.id.btnSensors);
		btnSensors.setText(R.string.sensors);

		btnSensors.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, Sensors.class);
				startActivity(in);
			}
		});

		final Button btnSensorsCheck = (Button) findViewById(R.id.btnSensorsCheck);
		btnSensorsCheck.setText(R.string.sensorscheck);

		btnSensorsCheck.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, SensorsCheck.class);
				startActivity(in);
			}
		});		

		final Button btnCamera = (Button) findViewById(R.id.btnCamera);
		btnCamera.setText(R.string.camera);

		btnCamera.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent mIntent = new Intent();
					ComponentName comp = new ComponentName(
							"com.android.camera", "com.android.camera.Camera");
					mIntent.setComponent(comp);
					mIntent.setAction("android.intent.action.VIEW");
					startActivity(mIntent);
				} catch (Exception e) {
					Log.e("###camera", e.toString());
				}
			}
		});

		final Button btnKeylight = (Button) findViewById(R.id.btnKeylight);
		btnKeylight.setText(R.string.keylight);
		
		btnKeylight.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(HardwareTest.this, Keylight.class);
				startActivity(in);
			}
		});
			
	}
}