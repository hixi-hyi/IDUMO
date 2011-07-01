/**
 *
 */
package com.hixi_hyi.android;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.provider.DataProviderInterface;
import com.hixi_hyi.android.receiptor.DataReceiptorInterface;


import android.app.Activity;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Hiroyoshi HOUCHI
 *
 */
public class Idumo extends Activity {

	private SensorManager sensorManager;
	private ArrayList<IdumoInterface> idumocomp;
	private LocationManager locationManager;

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		locationManager =(LocationManager)getSystemService(LOCATION_SERVICE);
		idumocomp = new ArrayList<IdumoInterface>();

	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		for(IdumoInterface i:idumocomp){
			i.onPause();
		}
		super.onPause();
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		for(IdumoInterface i:idumocomp){
			i.onDestroy();
		}
		super.onDestroy();
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		for(IdumoInterface i:idumocomp){
			i.onRestart();
		}
		super.onRestart();
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		for(IdumoInterface i:idumocomp){
			i.onStart();
		}
		super.onStart();
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		for(IdumoInterface i:idumocomp){
			i.onStop();
		}
		super.onStop();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		for(IdumoInterface i:idumocomp){
			i.onResume();
		}
		super.onResume();
	}

}
