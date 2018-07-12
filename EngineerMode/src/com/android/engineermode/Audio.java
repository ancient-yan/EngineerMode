package com.android.engineermode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.io.FileInputStream;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.provider.MediaStore;
import android.util.Log;
public class Audio extends Activity {
	
    private static final String TAG = "Engineer.Audio";
    
	private Button myButton1;
	private Button myButton2;
	private Button myButton3;
	private Button myButton4;
	private Button myButton5;
	private Button myButtonPlayStop;

	private ListView myListView1;
	private String strTempFile = "test_";
	private File myRecAudioFile;
	private File myRecAudioDir;
	private File myPlayFile;
	private MediaRecorder mMediaRecorder01;
	private MediaPlayer mediaPlayer; 
	private boolean bIsPaused = false;

	private ArrayList<String> recordFiles;
	private ArrayAdapter<String> adapter;
	private TextView myTextView1;
	private boolean sdCardExit;
	private boolean isStopRecord;
	private long mSampleStart;
	
	String autoRecFilePath;
	String autoRecFileName;
	Timer timer = new Timer();
	Timer timer2 = new Timer();
	Timer timer3 = new Timer();

	private EngineerModeApplication application;
	private SQLiteDatabase db;
	private int id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audio);
		
		application = (EngineerModeApplication) getApplication();
	    db = application.getSqLiteDatabase();
	    id = getIntent().getIntExtra(AutoHardwareTest._ID,0);
	    
		myButton1 = (Button) findViewById(R.id.ImageButton01);
		myButton2 = (Button) findViewById(R.id.ImageButton02);
		myButton3 = (Button) findViewById(R.id.ImageButton03);
		myButton4 = (Button) findViewById(R.id.ImageButton04);
		myButton5 = (Button) findViewById(R.id.ImageButton05);
		myButtonPlayStop = (Button) findViewById(R.id.ImageButtonPlayStop);
		
		myButton1.setText(R.string.audio_rec);
		myButton2.setText(R.string.audio_stop);
		myButton3.setText(R.string.audio_source1);
		myButton4.setText(R.string.audio_del);
		myButton5.setText(R.string.audio_source2);
		myButtonPlayStop.setText(R.string.audio_play_stop);
	
		myListView1 = (ListView) findViewById(R.id.ListView01);
		myTextView1 = (TextView) findViewById(R.id.TextView01);

		myButton2.setEnabled(false);
		myButton3.setEnabled(false);
		myButton4.setEnabled(false);
		myButton5.setEnabled(false);
		myButtonPlayStop.setEnabled(false);
		
		sdCardExit = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if (sdCardExit) {
			myRecAudioDir = Environment.getExternalStorageDirectory();
		}

		getRecordFiles();
		adapter = new ArrayAdapter<String>(this, R.layout.my_simple_list_item,recordFiles);
		myListView1.setAdapter(adapter);
		myButton1.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View arg0) {
				try {
					if (!sdCardExit) {
						Toast.makeText(Audio.this, getString(R.string.audio_sd_info),Toast.LENGTH_LONG).show();
						return;
					}
					myRecAudioFile = File.createTempFile(strTempFile, ".amr",myRecAudioDir);
					mMediaRecorder01 = new MediaRecorder();
					mMediaRecorder01.setAudioSource(MediaRecorder.AudioSource.MIC);
					mMediaRecorder01.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
					mMediaRecorder01.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
					mMediaRecorder01.setOutputFile(myRecAudioFile.getAbsolutePath());
					mMediaRecorder01.prepare();
					mMediaRecorder01.start();
                    mSampleStart = System.currentTimeMillis();
					myTextView1.setText(getString(R.string.audio_rec_info));
					myButton1.setEnabled(false);
					myButton2.setEnabled(true);
					myButton3.setEnabled(false);
					myButton4.setEnabled(false);
					myButton5.setEnabled(false);
					myButtonPlayStop.setEnabled(false);
					myListView1.setEnabled(false);
					isStopRecord = false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		myButton2.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View arg0) {
                long duration = System.currentTimeMillis() - mSampleStart;
                addToMediaDB(myRecAudioFile, duration);
				try {
					if (myRecAudioFile != null) {
						mMediaRecorder01.stop();
						mMediaRecorder01.release();
						mMediaRecorder01 = null;
						adapter.add(myRecAudioFile.getName());
						myTextView1.setText(getString(R.string.audio_rec_stop)+ myRecAudioFile.getName());
						myButton2.setEnabled(false);
						myButton1.setEnabled(true);
						myListView1.setEnabled(true);
						isStopRecord = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		myButton3.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View arg0) {
				try {
					if (myPlayFile != null && myPlayFile.exists()) {
						int mode = AudioManager.MODE_NORMAL;
						mode = AudioManager.MODE_RINGTONE;
						AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
						audioManager.setMode(mode);
						PlaySoundFile(myPlayFile);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		myButton5.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View arg0) {
				try {
					if (myPlayFile != null && myPlayFile.exists()) {
						int mode = AudioManager.MODE_NORMAL;
						mode = AudioManager.MODE_IN_CALL;
						AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
						audioManager.setMode(mode);
						PlaySoundFile(myPlayFile);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		myButton4.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View arg0) {
				deleteRecFile();
			}
		});
		
		
		myButtonPlayStop.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View arg0) {
				try {
					StopPlaySoundFile();
				} catch (Exception e) {
					Log.e("###StopPlaySoundFile", e.toString());
				}
			}
		});

		myListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				try {
					myButton3.setEnabled(true);
					myButton4.setEnabled(true);
					myButton5.setEnabled(true);
					myButtonPlayStop.setEnabled(true);
					myPlayFile = new File(myRecAudioDir
							.getAbsolutePath()
							+ File.separator
							+ ((CheckedTextView) arg1).getText());
					myTextView1.setText(getString(R.string.audio_file_choose)
									+ ((CheckedTextView) arg1).getText());
				} catch (Exception e) {
					e.printStackTrace();
					}
			}
		});
		if (id!=0){
			if(!CommonUtil.hasSd()){
				ContentValues values = new ContentValues();
				values.put(AutoHardwareTest.ITEM, getString(R.string.auto_audio_res_title));
				values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_UNKONW);
				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
				CommonUtil.autoNextActivity(Audio.this, db, id+1);
				finish();
				return;
			}
			myButton1.setEnabled(false);
			myButton2.setEnabled(false);
			myButton3.setEnabled(false);
			myButton4.setEnabled(false);
			myButton5.setEnabled(false);
			myButtonPlayStop.setEnabled(false);
			myListView1.setEnabled(false);
			try {
				new AlertDialog.Builder(Audio.this).setTitle(
						R.string.auto_audio_title_out).setMessage(
						R.string.auto_audio_info).setIcon(
						android.R.drawable.ic_dialog_info)
						.setPositiveButton(R.string.auto_audio_ok,new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								dialog.dismiss();
								autoRecFile();
								timer.schedule(task, 1000 * 3, 100000); 
								timer2.schedule(task2, 1000 * 4, 100000); 
								timer3.schedule(task3, 1000 * 7, 100000); 
							}
						}).show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteRecFile() {
		try {
			if (myPlayFile != null) {
				adapter.remove(myPlayFile.getName());
				if (myPlayFile.exists())
					myPlayFile.delete();
				myTextView1.setText(getString(R.string.audio_file_del));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onStop() {
		try {
			if (mMediaRecorder01 != null && !isStopRecord) {
				mMediaRecorder01.stop();
				mMediaRecorder01.release();
				mMediaRecorder01 = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		try {
			int mode = AudioManager.MODE_NORMAL;
			mode = AudioManager.MODE_RINGTONE;
			AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			audioManager.setMode(mode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onDestroy();
	}
	
	private void getRecordFiles() {
		try {
			recordFiles = new ArrayList<String>();
			if (sdCardExit) {
				File files[] = myRecAudioDir.listFiles();
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						if (files[i].getName().indexOf(".") >= 0) {
							String fileS = files[i].getName().substring(
									files[i].getName().indexOf("."));
							if (fileS.toLowerCase().equals(".amr"))
								recordFiles.add(files[i].getName());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void openFile(File f) {
		try {
			Intent intent = new Intent();
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setAction(android.content.Intent.ACTION_VIEW);
			String type = getMIMEType(f);
			intent.setDataAndType(Uri.fromFile(f), type);
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void PlaySoundFile(File f) {
        try { 
            if (mediaPlayer != null) { 
                mediaPlayer.stop(); 
            } 
            String fPathStr = Uri.fromFile(f).toString().substring(7);
            Log.e("######PlaySoundFile -- fPathStr",fPathStr);
            mediaPlayer = new MediaPlayer(); 
            mediaPlayer.setDataSource(fPathStr);
            mediaPlayer.prepare(); 
            mediaPlayer.start(); 
            myTextView1.setText(R.string.audio_play_on); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
	}
	
	private void StopPlaySoundFile(){
        if (mediaPlayer != null) { 
            mediaPlayer.stop(); 
            myTextView1.setText(R.string.audio_play_stop); 
        } 
	}
	
	private void PausePlaySoundFile(){
        if (mediaPlayer != null) { 
            if (!bIsPaused) { 
                mediaPlayer.pause(); 
                bIsPaused = true; 
                myTextView1.setText(R.string.audio_play_pause); 
            } else { 
                mediaPlayer.start(); 
                bIsPaused = false; 
                myTextView1.setText(R.string.audio_play_start); 
            } 
        } 
	}

	private String getMIMEType(File f) {
		try {
			String end = f.getName().substring(
					f.getName().lastIndexOf(".") + 1, f.getName().length())
					.toLowerCase();
			String type = "";
			if (end.equals("mp3") || end.equals("aac") || end.equals("aac")
					|| end.equals("amr") || end.equals("mpeg")
					|| end.equals("mp4")) {
				type = "audio";
			} else if (end.equals("jpg") || end.equals("gif")
					|| end.equals("png") || end.equals("jpeg")) {
				type = "image";
			} else {
				type = "*";
			}
			type += "/*";
			return type;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "audio";
	}

	
 
	public int getRecordSize(){
		try {
         	FileInputStream fs = new FileInputStream(myRecAudioFile);
         	int size = fs.available();
         	fs.close();
         	return size;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/*
	 * Add the given audioId to the playlist with the given playlistId; and maintain the
	 * play_order in the playlist.
	 */
	private void addToPlaylist(ContentResolver resolver, int audioId, long playlistId) {
	     String[] cols = new String[] {
	             "count(*)"
	     };
	     Uri uri = MediaStore.Audio.Playlists.Members.getContentUri("external", playlistId);
	     Cursor cur = resolver.query(uri, cols, null, null, null);
	     cur.moveToFirst();
	     final int base = cur.getInt(0);
	     cur.close();
	     ContentValues values = new ContentValues();
	     values.put(MediaStore.Audio.Playlists.Members.PLAY_ORDER, Integer.valueOf(base + audioId));
	     values.put(MediaStore.Audio.Playlists.Members.AUDIO_ID, audioId);
	     resolver.insert(uri, values);
	 }
 
	/*
	 * Obtain the id for the default play list from the audio_playlists table.
	 */
	private int getPlaylistId(Resources res) {
	     Uri uri = MediaStore.Audio.Playlists.getContentUri("external");
	     final String[] ids = new String[] { MediaStore.Audio.Playlists._ID };
	     final String where = MediaStore.Audio.Playlists.NAME + "=?";
	     final String[] args = new String[] { res.getString(R.string.audio_db_playlist_name) };
	     Cursor cursor = getContentResolver().query(uri, ids, where, args, null);
	     if (cursor == null) {
	         Log.v(TAG, "query returns null");
	     }
	     int id = -1;
	     if (cursor != null) {
	         cursor.moveToFirst();
	         if (!cursor.isAfterLast()) {
	             id = cursor.getInt(0);
	         }
	     }
	     cursor.close();
	     return id;
	 }
 
	/*
	 * Create a playlist with the given default playlist name, if no such playlist exists.
	 */
	private Uri createPlaylist(Resources res, ContentResolver resolver) {
	     ContentValues cv = new ContentValues();
	     cv.put(MediaStore.Audio.Playlists.NAME, res.getString(R.string.audio_db_playlist_name));
	     Uri uri = resolver.insert(MediaStore.Audio.Playlists.getContentUri("external"), cv);
	     return uri;
	 }
	
    /*
     * Adds file and returns content uri.
     */
    private Uri addToMediaDB(File file, long duration) {
    	Log.d(TAG, "addToMediaDB");
        Resources res = getResources();
        ContentValues cv = new ContentValues();
        long current = System.currentTimeMillis();
        long modDate = file.lastModified();
        Date date = new Date(current);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String title = formatter.format(date);
        Log.d(TAG, "Duration:"+duration);

        // Lets label the recorded audio file as NON-MUSIC so that the file
        // won't be displayed automatically, except for in the playlist.
        cv.put(MediaStore.Audio.Media.IS_MUSIC, "0");
        //++Save file size
        //linxu@2010-11-29
        cv.put(MediaStore.Audio.Media.SIZE, getRecordSize());
        cv.put(MediaStore.Audio.Media.TITLE, title);
        cv.put(MediaStore.Audio.Media.DATA, file.getAbsolutePath());
        cv.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
        cv.put(MediaStore.Audio.Media.DATE_MODIFIED, (int) (modDate / 1000));
        cv.put(MediaStore.Audio.Media.MIME_TYPE, "audio/amr");
        cv.put(MediaStore.Audio.Media.ARTIST,
                res.getString(R.string.audio_db_artist_name));         
        cv.put(MediaStore.Audio.Media.DURATION, duration);
        cv.put(MediaStore.Audio.Media.ALBUM,
                res.getString(R.string.audio_db_album_name));
        Log.d(TAG, "Inserting audio record: " + cv.toString());
    	ContentResolver resolver = getContentResolver();
        Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Log.d(TAG, "ContentURI: " + base);
        Uri	result = resolver.insert(base, cv);
        if (getPlaylistId(res) == -1) {
            createPlaylist(res, resolver);
        }
        int audioId = Integer.valueOf(result.getLastPathSegment());
        addToPlaylist(resolver, audioId, getPlaylistId(res));
        // Notify those applications such as Music listening to the 
        // scanner events that a recorded audio file just created. 
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, result));
        return result;
    }
    
    Handler handler = new Handler(){   
		  
        public void handleMessage(Message msg) {   
            switch (msg.what) {       
            case 1:
            	autoStopRec();
            	timer.cancel();
                break;    
            case 2:
            	autoPlaySoundBySPK();
            	timer2.cancel();
            	break; 
            case 3:
              	timer3.cancel();
				try {
					new CustomAlertDialog.Builder(Audio.this)
						.setTitle2(R.string.auto_test_choose,R.string.test_again,new View.OnClickListener() {
							public void onClick(View v) {
								CommonUtil.autoNextActivity(Audio.this, db, id);
								finish();
							}
						}).setMessage(R.string.auto_audio_res_info).setIcon(
							android.R.drawable.ic_dialog_info)
							.setPositiveButton(R.string.auto_test_no,new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {
									ContentValues values = new ContentValues();
		            				values.put(AutoHardwareTest.ITEM, getString(R.string.auto_audio_res_title));
	            					values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_FAILD);
		            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
		            				CommonUtil.autoNextActivity(Audio.this, db, id+1);
		            				finish();
								}
							}).setNegativeButton(R.string.auto_test_yes,new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {
									ContentValues values = new ContentValues();
		            				values.put(AutoHardwareTest.ITEM, getString(R.string.auto_audio_res_title));
	            					values.put(AutoHardwareTest.RESULT,AutoHardwareTest.RESULT_OK);
		            				db.update(AutoHardwareTest.TABLE_NAME, values, AutoHardwareTest._ID+" = "+id, null);
		            				CommonUtil.autoNextActivity(Audio.this, db, id+1);
		            				finish();
								}
							}).show();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					deleteRecFile();
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
    
    TimerTask task2 = new TimerTask(){   
  	  
        public void run() {   
            Message message = new Message();       
            message.what = 2;       
            handler.sendMessage(message);     
        }          
    };
    
    TimerTask task3 = new TimerTask(){   
    	  
        public void run() {   
            Message message = new Message();       
            message.what = 3;       
            handler.sendMessage(message);     
        }          
    };
    
	public void autoRecFile(){
		try{
			if (!sdCardExit) {
				Toast.makeText(Audio.this, getString(R.string.audio_sd_info),Toast.LENGTH_LONG).show();
				return;
			}
			myTextView1.setText(getString(R.string.audio_rec_info));
			myRecAudioFile = File.createTempFile(strTempFile, ".amr",myRecAudioDir);
			mMediaRecorder01 = new MediaRecorder();
			mMediaRecorder01.setAudioSource(MediaRecorder.AudioSource.MIC);
			mMediaRecorder01.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			mMediaRecorder01.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			mMediaRecorder01.setOutputFile(myRecAudioFile.getAbsolutePath());
			autoRecFilePath=myRecAudioFile.getAbsolutePath();
			autoRecFileName = autoRecFilePath.substring(autoRecFilePath.lastIndexOf(File.separator)+1);   
			mMediaRecorder01.prepare();
			mMediaRecorder01.start();
			isStopRecord = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void autoStopRec(){
		try {
			if (myRecAudioFile != null) {
				mMediaRecorder01.stop();
				mMediaRecorder01.release();
				mMediaRecorder01 = null;
				adapter.add(myRecAudioFile.getName());
				myTextView1.setText(getString(R.string.audio_rec_stop)+ myRecAudioFile.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void autoOpenFile(){
		try {
			myPlayFile = new File(autoRecFilePath);
			if (myPlayFile != null && myPlayFile.exists()) {
				int mode = AudioManager.MODE_NORMAL;
				mode = AudioManager.MODE_RINGTONE;
				AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				audioManager.setMode(mode);
				openFile(myPlayFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void autoPlaySoundBySPK(){
		try {
			myPlayFile = new File(autoRecFilePath);
			if (myPlayFile != null && myPlayFile.exists()) {
				int mode = AudioManager.MODE_NORMAL;
				mode = AudioManager.MODE_RINGTONE;
				AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				audioManager.setMode(mode);
				PlaySoundFile(myPlayFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void autoPlaySoundByREC(){
		try {
			myPlayFile = new File(autoRecFilePath);
			if (myPlayFile != null && myPlayFile.exists()) {
				int mode = AudioManager.MODE_NORMAL;
				mode = AudioManager.MODE_IN_CALL;
				AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				audioManager.setMode(mode);
				PlaySoundFile(myPlayFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
