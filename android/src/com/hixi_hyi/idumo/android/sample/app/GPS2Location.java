package com.hixi_hyi.idumo.android.sample.app;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.GPSSensor;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.component.ReversedGeocording;
import com.hixi_hyi.idumo.core.handler.ReversedGeocordingHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

/**
 * AndroidのGPS情報から現在地を推測します．
 *
 * @author Hiroyoshi
 *
 */
public class GPS2Location extends Activity implements Runnable {

	private ArrayList<ApplicationControlforAndroid> android;
	private GPSProvider gps;
	private GPSProvider gps2;

	private ReversedGeocordingHandler rgh;
	private TextViewReceiptor textView;
	private Thread thread;
	private boolean isDo;
	private Handler handler;

	@Override
	public void run() {
		while(true){
			LogManager.log();
			try {
				if(textView.isReady()){
					handler.post(textView);
					Thread.sleep(20000);
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<ApplicationControlforAndroid>();
		handler = new Handler();

		LocationManager location = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		GPSSensor gpsSensor = GPSSensor.INSTANCE;
		gpsSensor.init(location);
		android.add(gpsSensor);

		try {
			gps = new GPSProvider(gpsSensor);
			gps2 = new GPSProvider(gpsSensor);
			gps.setOption(GPSProvider.Type.LATITUDE);
			gps2.setOption(GPSProvider.Type.LONGITUDE);

			rgh = new ReversedGeocordingHandler();

			textView = new TextViewReceiptor(this);

			rgh.setSender(gps,gps2);
			textView.setSender(gps,gps2,rgh);
		} catch (IdumoException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void onStart() {
		super.onStart();
		for(ApplicationControlforAndroid a:android){
			a.onStart();
		}
	}

	@Override
	public void onRestart() {
		for(ApplicationControlforAndroid a:android){
			a.onRestart();
		}
		super.onRestart();
	}

	@Override
	public void onResume() {
		super.onResume();
		for(ApplicationControlforAndroid a:android){
			a.onResume();
		}
		isDo = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onPause() {
		for(ApplicationControlforAndroid a:android){
			a.onPause();
		}
		isDo = false;
		super.onPause();
	}

	@Override
	public void onStop() {
		for(ApplicationControlforAndroid a:android){
			a.onStop();
		}
		super.onStop();
	}

	@Override
	public void onDestroy() {
		for(ApplicationControlforAndroid a:android){
			a.onDestroy();
		}
		super.onDestroy();
	}

}
