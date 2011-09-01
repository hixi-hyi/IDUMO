package com.hixi_hyi.idumo.android.sample;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.AccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.util.LogManager;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

public class Accelerometer2TCP extends Activity implements Runnable{

	private ArrayList<ApplicationControlforAndroid> android;
	private SensorManager sensormanager;
	private AccelerometerProvider accelerometer;
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
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<ApplicationControlforAndroid>();
		handler = new Handler();

		sensormanager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		AccelerometerSensor accelSensor = AccelerometerSensor.INSTANCE;
		accelSensor.init(sensormanager);
		android.add(accelSensor);
		accelerometer = new AccelerometerProvider(accelSensor);

		through = new ThroughHandler();

		textView = new TextViewReceiptor(this);


		textView.setSender(through);
		through.setSender(accelerometer);

		accelerometer.setOption(AccelerometerProvider.Type.X);
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
