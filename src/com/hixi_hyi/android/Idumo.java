/**
 *
 */
package com.hixi_hyi.android;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.handler.imp.DebugHandler;
import com.hixi_hyi.android.handler.imp.DelayHandler;
import com.hixi_hyi.android.handler.imp.GoogleBarChartHandler;
import com.hixi_hyi.android.handler.imp.ImpoundHandler;
import com.hixi_hyi.android.handler.imp.TCPByteStreamHandler;
import com.hixi_hyi.android.handler.imp.TCPStringStreamHandler;
import com.hixi_hyi.android.handler.imp.transform.JoinStringHandler;
import com.hixi_hyi.android.handler.imp.transform.ToStringHandler;
import com.hixi_hyi.android.provider.DataProviderInterface;
import com.hixi_hyi.android.provider.imp.AccelerometerProvider;
import com.hixi_hyi.android.provider.imp.AccelerometerXProvider;
import com.hixi_hyi.android.provider.imp.AccelerometerYProvider;
import com.hixi_hyi.android.provider.imp.GPSProvider;
import com.hixi_hyi.android.receiptor.DataReceiptor;
import com.hixi_hyi.android.receiptor.DataReceiptorInterface;
import com.hixi_hyi.android.receiptor.imp.DebugReceiptor;
import com.hixi_hyi.android.receiptor.imp.ImageViewReceiptor;
import com.hixi_hyi.android.receiptor.imp.TextReceiptor;
import com.hixi_hyi.android.receiptor.imp.TextViewReceiptor;

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

		// Provider
		AccelerometerProvider accelProvider = new AccelerometerProvider(sensorManager);

		TCPStringStreamHandler tcpHandler = null;
		try {
			tcpHandler = new TCPStringStreamHandler("172.21.67.142", 10000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DebugHandler debugHandler = new DebugHandler();

		JoinStringHandler tostringHandler = new JoinStringHandler(",");


		if(tostringHandler.isRegist(accelProvider)){
//			Log.v(this.getClass().getSimpleName(), "Registed:"+accelProvider.getClass().getSimpleName()+"->"+tostringHandler.getClass().getSimpleName());
			accelProvider.addProviderListener(tostringHandler);
		}

		if(debugHandler.isRegist(tostringHandler)){
			Log.v(this.getClass().getSimpleName(), "Registed:"+tostringHandler.getClass().getSimpleName()+"->"+debugHandler.getClass().getSimpleName());
			tostringHandler.addHandlerListener(debugHandler);
		}
		if(tcpHandler.isRegist(tostringHandler)){
//			Log.v(this.getClass().getSimpleName(), "Registed:"+tostringHandler.getClass().getSimpleName()+"->"+tcpHandler.getClass().getSimpleName());
			tostringHandler.addHandlerListener(tcpHandler);
		}

//		GPSProvider gps = new GPSProvider(locationManager);
//		idumocomp.add(gps);

		idumocomp.add(accelProvider);
		idumocomp.add(tcpHandler);
		idumocomp.add(debugHandler);
		idumocomp.add(tostringHandler);

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
