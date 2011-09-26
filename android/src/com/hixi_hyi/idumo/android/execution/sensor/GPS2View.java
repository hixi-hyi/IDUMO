package com.hixi_hyi.idumo.android.execution.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.GPSSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class GPS2View extends Activity implements Runnable {
	
	private ArrayList<AndroidController>	android;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<AndroidController>();
		handler = new Handler();
		
		LocationManager location = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		GPSSensor gpsSensor = GPSSensor.INSTANCE;
		gpsSensor.init(location);
		android.add(gpsSensor);
		
		GPSProvider gps = new GPSProvider(gpsSensor);
		GPSProvider gps2 = new GPSProvider(gpsSensor);
		GPSProvider gps3 = new GPSProvider(gpsSensor);
		GPSProvider gps4 = new GPSProvider(gpsSensor);
		GPSProvider gps5 = new GPSProvider(gpsSensor);
		GPSProvider gps6 = new GPSProvider(gpsSensor);
		GPSProvider gps7 = new GPSProvider(gpsSensor);
		try {
			gps.setOption(GPSProvider.Type.ACCURARY);
			gps2.setOption(GPSProvider.Type.ALTITUDE);
			gps3.setOption(GPSProvider.Type.BEARING);
			gps4.setOption(GPSProvider.Type.LATITUDE);
			gps5.setOption(GPSProvider.Type.LONGITUDE);
			gps6.setOption(GPSProvider.Type.SPEED);
			gps7.setOption(GPSProvider.Type.TIME);
		} catch (IdumoException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		StringConcatHandler s1 = new StringConcatHandler("Accuary:");
		StringConcatHandler s2 = new StringConcatHandler("Altitude:");
		StringConcatHandler s3 = new StringConcatHandler("Bearing:");
		StringConcatHandler s4 = new StringConcatHandler("Latitude:");
		StringConcatHandler s5 = new StringConcatHandler("Longitude:");
		StringConcatHandler s6 = new StringConcatHandler("Speed:");
		StringConcatHandler s7 = new StringConcatHandler("Time:");
		
		textView = new TextViewReceiptor(this);
		
		s1.setSender(gps);
		s2.setSender(gps2);
		s3.setSender(gps3);
		s4.setSender(gps4);
		s5.setSender(gps5);
		s6.setSender(gps6);
		s7.setSender(gps7);
		
		textView.setSender(s1, s2, s3, s4, s5, s6, s7);
		
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
