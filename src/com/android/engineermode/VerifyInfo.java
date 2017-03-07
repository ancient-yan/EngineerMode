package com.android.engineermode;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.util.Log;
import android.util.Config;
import android.os.Bundle;
import android.content.Context;
import java.io.IOException;

public class VerifyInfo extends ListActivity {
	private String[] m_titles;
	private byte[] verifyId = null;
	private String passDisplay;
	private String failDisplay;
	private String untestDisplay;
	private String unknownDisplay;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

			m_titles=new String[9];

	    m_titles[0]=getString(R.string.sn_info);
	    m_titles[1]=getString(R.string.sn_verify);
	    m_titles[2]=getString(R.string.cdma_correct_verify);
	    m_titles[3]=getString(R.string.at_verify);
	    m_titles[4]=getString(R.string.cdma_antenna_verify);
	    m_titles[5]=getString(R.string.meid_verify);
	    m_titles[6]=getString(R.string.gps_verify);
	    m_titles[7]=getString(R.string.wifi_verify);
	    m_titles[8]=getString(R.string.bt_verify);
	    
	    passDisplay = getString(R.string.pass_display);
	    failDisplay = getString(R.string.fail_display);
	    untestDisplay = getString(R.string.untest_display);
	    unknownDisplay = getString(R.string.unknown_display);
	
	    setListAdapter(new VerifyInfoAdapter(this));
	}

	private class VerifyInfoAdapter extends BaseAdapter {
		
		private LayoutInflater m_inflater;
		
		public VerifyInfoAdapter(Context context) {
			m_inflater = LayoutInflater.from(context);
		}
		
		public int getCount() {
			return m_titles.length;
		}

		public Object getItem(int position) {
			return m_titles[position];
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null)
				convertView = m_inflater.inflate(R.layout.twoline_list_item, null);
			
			TextView title = (TextView)convertView.findViewById(R.id.title);
			TextView info = (TextView)convertView.findViewById(R.id.info);
			
			title.setText(m_titles[position]);
			switch(position) {
			case 0:
				info.setText(getSN());
				break;
				
			case 1:
				info.setText(getSNVerify());
				break;
				
			case 2:
				info.setText(getCDMACorrectVerify());
				break;
				
			case 3:
				info.setText(getAtVerify());
				break;
				
			case 4:
				info.setText(getCDMAAntennaVerify());
				break;
			
			case 5:
				info.setText(getMEIDVerify());
				break;
				case 6:
				info.setText(getGPSVerify());
				break;
				case 7:
				info.setText(getWIFIVerify());
				 break;
				 case 8:
				 info.setText(getBTVerify());
				 break;
				
			default:
				info.setText("unknown");
				break;
			}
			return convertView;
		}
		
		private String getAtVerify() {
			if (verifyId == null) {
				return untestDisplay;
			}
			return getVerifyDisplay((int)verifyId[22]);
		}
		
		private String getSNVerify() {
			if (verifyId == null) {
				return untestDisplay;
			}
			return getVerifyDisplay((int)verifyId[20]);
		}
		
		private String getCDMACorrectVerify() {
			if (verifyId == null) {
				return untestDisplay;
			}
			return getVerifyDisplay((int)verifyId[21]);
		}
		
		private String getCDMAAntennaVerify() {
			if (verifyId == null) {
				return untestDisplay;
			}
			return getVerifyDisplay((int)verifyId[23]);
		}
		
		private String getMEIDVerify() {
			if (verifyId == null) {
				return untestDisplay;
			}
			return getVerifyDisplay((int)verifyId[24]);
		}
		
		private String getGPSVerify() {
			if (verifyId == null) {
				return untestDisplay;
			}
			return getVerifyDisplay((int)verifyId[25]);
		}
		
		private String getWIFIVerify() {
			if (verifyId == null) {
				return untestDisplay;
			}
			return getVerifyDisplay((int)verifyId[26]);
		}
		
		private String getBTVerify() {
			if (verifyId == null) {
				return untestDisplay;
			}
			return getVerifyDisplay((int)verifyId[27]);
		}

		private String getSN() {
			if (verifyId == null) {
				return "";
			}
			String result = new String("");
			for (int i = 0; i < 20; i++) {
				if ((verifyId[i] & 0xff) == 0) {
					Log.e("VerifyInfo", "i="+i);
					break;
				}
				Log.e("VerifyInfo", "char="+(int)verifyId[i]);
				result += new String(new char[]{(char)verifyId[i]});
			}
			
			return result;
		}
		
		private String getVerifyDisplay(int i) {
			switch (i) {
				case 0:
					return untestDisplay;
				case 1:
					return failDisplay;
				case 3: 
					return passDisplay;
				default:
					return unknownDisplay + "(" + i + ")";
			}
		}
	}
}
