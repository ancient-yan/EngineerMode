package com.android.engineermode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TouchscreenCheck extends Activity {
	
	private boolean isCorrect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touchscreen_check);
		final Button btnStart = (Button)findViewById(R.id.btnStart);
		btnStart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					btnStart.setEnabled(false);
					final ProgressDialog dialog = new ProgressDialog(TouchscreenCheck.this);
					dialog.setMessage(getString(R.string.touchscreen_checking));
					dialog.setOnDismissListener(new OnDismissListener() {
						public void onDismiss(DialogInterface dialog) {
							String message;
							if(isCorrect){
								message = getString(R.string.touchscreencheck_success);
							}else{
								message = getString(R.string.touchscreencheck_fail);
							}
							new AlertDialog.Builder(TouchscreenCheck.this).setMessage(message).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									btnStart.setEnabled(true);
								}
							}).show();
						}
					});
					dialog.show();
			        new Thread(){
			        	public void run() {
			        		FileWriter fstream = null;
					        BufferedWriter out = null;
			        		try {
			        			sleep(2000);
				        		fstream = new FileWriter("/proc/touchscreen/enabletouchscreen");
						        out = new BufferedWriter(fstream);
								Log.v("----------TouchscreenCheck----------","Now send calibration cmd to TP driver ");
						        out.write("2" + "\n");

								// close
								out.close();
		       					fstream.close();								
						        sleep(3000);
								
								// re-create fstream and out class
								fstream = new FileWriter("/proc/touchscreen/enabletouchscreen");
						        out = new BufferedWriter(fstream);
								Log.v("----------TouchscreenCheck----------","Now send RESET cmd to TP driver ");
						        out.write("3" + "\n");

								// close
								out.close();
		       					fstream.close();
						        sleep(1000);
								
						        String result = readTouchInfo();
						        Log.v("----------TouchscreenCheck----------","TouchscreenCheck completed, result=" + result);
						        isCorrect = (result!=null&&result.startsWith("ilitek5a"));
						        dialog.dismiss();
							} catch (Exception e) {
								e.printStackTrace(System.err);
							} finally {
								try{
															
								}catch(Exception e){
									e.printStackTrace(System.err);
								}
							}
			        	};
			        }.start();
		        }catch (Exception e) {
		            e.printStackTrace(System.err);
		        }
			}
		});
	}
	
	private String readTouchInfo() {
		String strTouchInfo = null;
		try {
			FileInputStream fileIS = new FileInputStream("/proc/touchscreen/enabletouchscreen");
			BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));
			String readString = new String();
			while ((readString = buf.readLine()) != null) {
				strTouchInfo = readString;
				Log.e("----------TouchscreenCheck----------readString----------", readString);
			}
			buf.close();           // Tiandf @ 2011-12-01
			fileIS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strTouchInfo;
	}
	
}
