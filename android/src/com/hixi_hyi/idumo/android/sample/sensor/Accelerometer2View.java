package com.hixi_hyi.idumo.android.sample.sensor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.AccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.android.util.LogUtil;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

public class Accelerometer2View extends Activity implements Runnable {

	private ArrayList<ApplicationControlforAndroid> android;
	private SensorManager sensor;
	private AccelerometerProvider accelerometer;
	private TextViewReceiptor textView;
	private Thread thread;
	private boolean isDo;
	private Handler handler;

	@Override
	public void run() {
		while(isDo){
			LogUtil.d();
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
		android = new ArrayList<ApplicationControlforAndroid>();
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
		textView.setSender(concat,concat2,concat3);


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