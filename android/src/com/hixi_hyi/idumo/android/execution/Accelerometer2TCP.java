package com.hixi_hyi.idumo.android.execution;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.AccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Accelerometer2TCP extends Activity implements Runnable {

	private ArrayList<AndroidController>	android;
	private SensorManager					sensormanager;
	private AccelerometerProvider			accelerometer;
	private ThroughHandler					through;
	private TextViewReceiptor				textView;
	private Thread							thread;
	private boolean							isDo;
	private Handler							handler;

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

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<AndroidController>();
		handler = new Handler();

		sensormanager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		AccelerometerSensor accelSensor = AccelerometerSensor.INSTANCE;
		accelSensor.init(sensormanager);
//		android.add(accelSensor);
//		accelerometer = new AccelerometerProvider(accelSensor);

		through = new ThroughHandler();

		textView = new TextViewReceiptor(this);

		textView.setSender(through);
		through.setSender(accelerometer);

		accelerometer.setOption(AccelerometerProvider.Type.X);
	}

	@Override
	public void onStart() {
		super.onStart();
		for (AndroidController a : android) {
			a.onIdumoStart();
		}
	}

	@Override
	public void onRestart() {
		for (AndroidController a : android) {
			a.onIdumoRestart();
		}
		super.onRestart();
	}

	@Override
	public void onResume() {
		super.onResume();
		for (AndroidController a : android) {
			a.onIdumoResume();
		}
		isDo = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onPause() {
		for (AndroidController a : android) {
			a.onIdumoPause();
		}
		isDo = false;
		super.onPause();
	}

	@Override
	public void onStop() {
		for (AndroidController a : android) {
			a.onIdumoStop();
		}
		super.onStop();
	}

	@Override
	public void onDestroy() {
		for (AndroidController a : android) {
			a.onIdumoDestroy();
		}
		super.onDestroy();
	}

}