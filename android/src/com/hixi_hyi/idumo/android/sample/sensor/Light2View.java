package com.hixi_hyi.idumo.android.sample.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.provider.LightProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.LightSensor;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Light2View extends Activity implements Runnable {
	
	private ArrayList<AndroidController>	android;
	private LightProvider					light;
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
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<AndroidController>();
		handler = new Handler();
		
		SensorManager sensor = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		
		LightSensor lightSensor = LightSensor.INSTANCE;
		lightSensor.init(sensor);
		android.add(lightSensor);
		
		light = new LightProvider(lightSensor);
		
		StringConcatHandler s1 = new StringConcatHandler("Light:");
		
		textView = new TextViewReceiptor(this);
		
		s1.setSender(light);
		textView.setSender(s1);
		
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
