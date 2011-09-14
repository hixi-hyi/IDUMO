package com.hixi_hyi.idumo.android.sample.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.ApplicationControllerForAndroid;
import com.hixi_hyi.idumo.android.provider.AccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Accelerometer2View extends Activity implements Runnable {
	
	private ArrayList<ApplicationControllerForAndroid>	android;
	private SensorManager								sensor;
	private AccelerometerProvider						accelerometer;
	private TextViewReceiptor							textView;
	private Thread										thread;
	private boolean										isDo;
	private Handler										handler;
	
	@Override
	public void run() {
		while (isDo) {
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<ApplicationControllerForAndroid>();
		handler = new Handler();
		
		sensor = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		AccelerometerSensor accelerometerSensor = AccelerometerSensor.INSTANCE;
		accelerometerSensor.init(sensor);
		android.add(accelerometerSensor);
		
		accelerometer = new AccelerometerProvider(accelerometerSensor);
		accelerometer.setOption(AccelerometerProvider.Type.X);
		AccelerometerProvider accelerometer2 = new AccelerometerProvider(accelerometerSensor);
		accelerometer2.setOption(AccelerometerProvider.Type.Y);
		AccelerometerProvider accelerometer3 = new AccelerometerProvider(accelerometerSensor);
		accelerometer3.setOption(AccelerometerProvider.Type.Z);
		
		StringConcatHandler concat = new StringConcatHandler("X:");
		StringConcatHandler concat2 = new StringConcatHandler("Y:");
		StringConcatHandler concat3 = new StringConcatHandler("Z:");
		
		textView = new TextViewReceiptor(this);
		
		concat.setSender(accelerometer);
		concat2.setSender(accelerometer2);
		concat3.setSender(accelerometer3);
		textView.setSender(concat, concat2, concat3);
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		for (ApplicationControllerForAndroid a : android) {
			a.onIdumoStart();
		}
	}
	
	@Override
	public void onRestart() {
		for (ApplicationControllerForAndroid a : android) {
			a.onIdumoRestart();
		}
		super.onRestart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		for (ApplicationControllerForAndroid a : android) {
			a.onIdumoResume();
		}
		isDo = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void onPause() {
		for (ApplicationControllerForAndroid a : android) {
			a.onIdumoPause();
		}
		isDo = false;
		super.onPause();
	}
	
	@Override
	public void onStop() {
		for (ApplicationControllerForAndroid a : android) {
			a.onIdumoStop();
		}
		super.onStop();
	}
	
	@Override
	public void onDestroy() {
		for (ApplicationControllerForAndroid a : android) {
			a.onIdumoDestroy();
		}
		super.onDestroy();
	}
	
}
