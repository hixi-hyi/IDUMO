package com.hixi_hyi.idumo.android.sample.sensor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.ProximityProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.ProximitySensor;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

public class Proximity2View extends Activity implements Runnable {

	private ArrayList<ApplicationControlforAndroid> android;
	private ProximityProvider prom;
	private TextViewReceiptor textView;
	private Thread thread;
	private boolean isDo;
	private Handler handler;

	@Override
	public void run() {
		while(isDo){
			LogManager.log();
			handler.post(textView);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<ApplicationControlforAndroid>();
		handler = new Handler();

		SensorManager sensor = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		ProximitySensor proximitySensor = ProximitySensor.INSTANCE;
		proximitySensor.init(sensor);
		android.add(proximitySensor);

		prom = new ProximityProvider(proximitySensor);

		StringConcatHandler s1 = new StringConcatHandler("Proximity:");

		textView = new TextViewReceiptor(this);

		s1.setSender(prom);
		textView.setSender(s1);


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
