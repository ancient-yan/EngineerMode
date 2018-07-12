package com.android.engineermode;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BatteryInfo extends Activity {
	private TextView statusView;
	private TextView healthView;
	private TextView levelView;
	private TextView scaleView;
	private TextView pluggedView;
	private TextView voltageView;
	private TextView temperatureView;
	private TextView technologyView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battery);
	}

	@Override
	protected void onResume() {
		super.onResume();

		IntentFilter filter = new IntentFilter();

		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(mBroadcastReceiver, filter);
	}

	@Override
	protected void onPause() {
		super.onPause();

		unregisterReceiver(mBroadcastReceiver);
	}

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
				int status = intent.getIntExtra("status", 0);
				int health = intent.getIntExtra("health", 0);
				boolean present = intent.getBooleanExtra("present", false);
				int level = intent.getIntExtra("level", 0);
				int scale = intent.getIntExtra("scale", 0);
				int icon_small = intent.getIntExtra("icon-small", 0);
				int plugged = intent.getIntExtra("plugged", 0);
				int voltage = intent.getIntExtra("voltage", 0);
				int temperature = intent.getIntExtra("temperature", 0);
				String technology = intent.getStringExtra("technology");

				String statusString = "";

				switch (status) {
				case BatteryManager.BATTERY_STATUS_UNKNOWN:
					statusString = getString(R.string.battery_status_unknown);
					break;
				case BatteryManager.BATTERY_STATUS_CHARGING:
					statusString = getString(R.string.battery_status_charging);
					break;
				case BatteryManager.BATTERY_STATUS_DISCHARGING:
					statusString = getString(R.string.battery_status_discharging);
					break;
				case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
					statusString = getString(R.string.battery_status_not_charging);
					break;
				case BatteryManager.BATTERY_STATUS_FULL:
					statusString = getString(R.string.battery_status_full);
					break;
				}

				String healthString = "";

				switch (health) {
				case BatteryManager.BATTERY_HEALTH_UNKNOWN:
					healthString = getString(R.string.battery_health_unknown);
					break;
				case BatteryManager.BATTERY_HEALTH_GOOD:
					healthString = getString(R.string.battery_health_good);
					break;
				case BatteryManager.BATTERY_HEALTH_OVERHEAT:
					healthString = getString(R.string.battery_health_overheat);
					break;
				case BatteryManager.BATTERY_HEALTH_DEAD:
					healthString = getString(R.string.battery_health_dead);
					break;
				case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
					healthString = getString(R.string.battery_health_voltage);
					break;
				case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
					healthString = getString(R.string.battery_health_unspecified_failure);
					break;
				}

				String acString = "";

				switch (plugged) {
				case BatteryManager.BATTERY_PLUGGED_AC:
					acString = getString(R.string.battery_plugged_ac);
					break;
				case BatteryManager.BATTERY_PLUGGED_USB:
					acString = getString(R.string.battery_plugged_usb);
					break;
				}

				temperature = temperature / 10;

				statusView = (TextView) findViewById(R.id.status);
				healthView = (TextView) findViewById(R.id.health);
				levelView = (TextView) findViewById(R.id.level);
				scaleView = (TextView) findViewById(R.id.scale);
				pluggedView = (TextView) findViewById(R.id.plugged);
				voltageView = (TextView) findViewById(R.id.voltage);
				temperatureView = (TextView) findViewById(R.id.temperature);
				technologyView = (TextView) findViewById(R.id.technology);

				statusView.setText(getString(R.string.battery_status)
						+ statusString);
				healthView.setText(getString(R.string.battery_health)
						+ healthString);
				levelView.setText(getString(R.string.battery_level)
						+ String.valueOf(level));
				scaleView.setText(getString(R.string.battery_scale)
						+ String.valueOf(scale));
				pluggedView.setText(getString(R.string.battery_plugged)
						+ acString);
				voltageView.setText(getString(R.string.battery_voltage)
						+ String.valueOf(voltage) + getString(R.string.battery_voltage_t));
				temperatureView.setText(getString(R.string.battery_temperature)
						+ String.valueOf(temperature)+getString(R.string.battery_temperature_t));
				technologyView.setText(getString(R.string.battery_technology)
						+ technology);

				try{
					Log.v("status", statusString);
					Log.v("health", healthString);
					Log.v("present", String.valueOf(present));
					Log.v("level", String.valueOf(level));
					Log.v("scale", String.valueOf(scale));
					Log.v("icon_small", String.valueOf(icon_small));
					Log.v("plugged", acString);
					Log.v("voltage", String.valueOf(voltage));
					Log.v("temperature", String.valueOf(temperature));
					Log.v("technology", technology);
				}catch(NullPointerException e){
					e.printStackTrace();
				}
			}
		}
	};
}
