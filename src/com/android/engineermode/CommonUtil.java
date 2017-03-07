package com.android.engineermode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class CommonUtil {

	public static boolean isStringEmpty(String string) {
		return string == null || string.equals("");
	}

	public static boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static FileOutputStream getFileOutputStream(String filePath)
			throws IOException {
		FileOutputStream out;
		File file = new File(filePath);
		if (file.getParentFile().exists()) {
			file.getParentFile().delete();
		}
		file.getParentFile().mkdirs();
		file.createNewFile();
		out = new FileOutputStream(file);
		return out;
	}

	public static boolean hasSd() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	public static boolean copyFile(InputStream in, String destFileName) {
		FileOutputStream out = null;
		byte[] buffer = new byte[10240];
		try {
			File destFile = new File(destFileName);
			if(destFile.exists()){
				return true;
			}
			destFile.delete();
			destFile.getParentFile().mkdirs();
			destFile.createNewFile();
			out = new FileOutputStream(destFile);
			int num = 0;
			while ((num = in.read(buffer)) != -1) {
				out.write(buffer, 0, num);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void autoNextActivity(Context context, SQLiteDatabase db, int id) {
		Cursor cursor = db.query(AutoHardwareTest.TABLE_NAME, null, AutoHardwareTest._ID+" = "+id, null, null, null,null);
		try {
			 cursor.moveToFirst();
			 String className = cursor.getString(cursor.getColumnIndex(AutoHardwareTest.CLASS));
			Intent intentAuto = new Intent(context,Class.forName(className));
			intentAuto.putExtra(AutoHardwareTest._ID, id);
			context.startActivity(intentAuto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cursor!=null)
				cursor.close();
		}
	}

}
