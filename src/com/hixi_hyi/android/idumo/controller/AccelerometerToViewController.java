package com.hixi_hyi.android.idumo.controller;

import java.util.ArrayList;

import com.hixi_hyi.android.idumo.AndroidInterface;
import com.hixi_hyi.android.idumo.provider.AccelerometerProvider;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;

public class AccelerometerToViewController implements AndroidControllerInterface {

	private Activity activity;
	private ArrayList<AndroidInterface> android;
	private SensorManager sensor;
	private AccelerometerProvider accelerometer;
	private Thread thread;
	private boolean isDo;

	@Override
	public void run() {
		while(isDo){
			if(accelerometer.isReady()){
				System.out.println(accelerometer.getData());
			}
		}
	}

	@Override
	public void onCreate(Activity activity) {
		this.activity = activity;
		android = new ArrayList<AndroidInterface>();
		sensor = (SensorManager) activity.getSystemService(Activity.SENSOR_SERVICE);
		accelerometer = new AccelerometerProvider(sensor);
		android.add(accelerometer);

	}

	@Override
	public void onStart() {
		for(AndroidInterface a:android){
			a.onStart();
		}
	}

	@Override
	public void onRestart() {
		for(AndroidInterface a:android){
			a.onRestart();
		}
	}

	@Override
	public void onResume() {
		for(AndroidInterface a:android){
			a.onResume();
		}
		isDo = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onPause() {
		for(AndroidInterface a:android){
			a.onPause();
		}
		isDo = false;
	}

	@Override
	public void onStop() {
		for(AndroidInterface a:android){
			a.onStop();
		}
	}

	@Override
	public void onDestroy() {
		for(AndroidInterface a:android){
			a.onDestroy();
		}
	}

}
