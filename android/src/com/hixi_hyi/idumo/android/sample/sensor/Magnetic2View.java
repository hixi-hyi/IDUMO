package com.hixi_hyi.idumo.android.sample.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.provider.MagneticFiledProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Magnetic2View extends Activity implements Runnable {
	
	private ArrayList<AndroidController>	android;
	private MagneticFiledProvider			mag;
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
		MagneticFieldSensor magneticFieldSensor = MagneticFieldSensor.INSTANCE;
		magneticFieldSensor.init(sensor);
		android.add(magneticFieldSensor);
		
		MagneticFiledProvider mag1 = new MagneticFiledProvider(magneticFieldSensor);
		MagneticFiledProvider mag2 = new MagneticFiledProvider(magneticFieldSensor);
		MagneticFiledProvider mag3 = new MagneticFiledProvider(magneticFieldSensor);
		try {
			mag1.setOption(MagneticFiledProvider.Type.X);
			mag2.setOption(MagneticFiledProvider.Type.Y);
			mag3.setOption(MagneticFiledProvider.Type.Z);
		} catch (IdumoException e) {
			e.printStackTrace();
		}
		
		StringConcatHandler s1 = new StringConcatHandler("X:");
		StringConcatHandler s2 = new StringConcatHandler("Y:");
		StringConcatHandler s3 = new StringConcatHandler("Z:");
		
		textView = new TextViewReceiptor(this);
		
		s1.setSender(mag1);
		s2.setSender(mag2);
		s3.setSender(mag3);
		
		textView.setSender(s1, s2, s3);
		
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
