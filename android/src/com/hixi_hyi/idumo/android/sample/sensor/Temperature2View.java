package com.hixi_hyi.idumo.android.sample.sensor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControllerForAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.TemperatureProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.TemperatureSensor;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.util.LogManager;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

public class Temperature2View extends Activity implements Runnable {

	private ArrayList<ApplicationControllerForAndroid> android;
	private TemperatureProvider provider;
	private ThroughHandler through;
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
		android = new ArrayList<ApplicationControllerForAndroid>();
		handler = new Handler();

		SensorManager sensor = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		TemperatureSensor temperatureSensor = TemperatureSensor.INSTANCE;
		temperatureSensor.init(sensor);
		android.add(temperatureSensor);

		provider = new TemperatureProvider(temperatureSensor);

		through = new ThroughHandler();

		textView = new TextViewReceiptor(this);


		if(!textView.setSender(through)){
			throw new RuntimeException();
		}
		if(!through.setSender(provider)){
			throw new RuntimeException();
		}

	}

	@Override
	public void onStart() {
		super.onStart();
		for(ApplicationControllerForAndroid a:android){
			a.onIdumoStart();
		}
	}

	@Override
	public void onRestart() {
		for(ApplicationControllerForAndroid a:android){
			a.onIdumoRestart();
		}
		super.onRestart();
	}

	@Override
	public void onResume() {
		super.onResume();
		for(ApplicationControllerForAndroid a:android){
			a.onIdumoResume();
		}
		isDo = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onPause() {
		for(ApplicationControllerForAndroid a:android){
			a.onIdumoPause();
		}
		isDo = false;
		super.onPause();
	}

	@Override
	public void onStop() {
		for(ApplicationControllerForAndroid a:android){
			a.onIdumoStop();
		}
		super.onStop();
	}

	@Override
	public void onDestroy() {
		for(ApplicationControllerForAndroid a:android){
			a.onIdumoDestroy();
		}
		super.onDestroy();
	}

}
