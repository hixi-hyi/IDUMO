package com.hixi_hyi.idumo.android.sample.app;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.AccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.provider.LivedoorWeatherProvider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

public class LivedoorWeather2MultiView extends Activity implements Runnable {

	private ArrayList<ApplicationControlforAndroid> android;
	private TextViewReceiptor textView;
	private Thread thread;
	private boolean isDo;
	private Handler handler;

	@Override
	public void run() {
		while(isDo){
			if(textView.isReady()){
				handler.post(textView);
				break;
			}
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

		try {
			LivedoorWeatherProvider date = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider locate = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider maxtemp = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider mintemp = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider weather = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider description = new LivedoorWeatherProvider(63);

			date.setOption(LivedoorWeatherProvider.Type.DATE);
			locate.setOption(LivedoorWeatherProvider.Type.LOCATION);
			maxtemp.setOption(LivedoorWeatherProvider.Type.MAX_TEMP);
			mintemp.setOption(LivedoorWeatherProvider.Type.MIN_TEMP);
			weather.setOption(LivedoorWeatherProvider.Type.WEATHER);
			description.setOption(LivedoorWeatherProvider.Type.DESCRIPTION);

			StringConcatHandler s1 = new StringConcatHandler("DATE:");
			StringConcatHandler s2 = new StringConcatHandler("Location:");
			StringConcatHandler s3 = new StringConcatHandler("Max:");
			StringConcatHandler s4 = new StringConcatHandler("Min:");
			StringConcatHandler s5 = new StringConcatHandler("Weather:");
			StringConcatHandler s6 = new StringConcatHandler("Desc:");

			s1.setSender(date);
			s2.setSender(locate);
			s3.setSender(maxtemp);
			s4.setSender(mintemp);
			s5.setSender(weather);
			s6.setSender(description);

			textView = new TextViewReceiptor(this);

			textView.setSender(s1,s2,s3,s4,s5,s6);
		} catch (IdumoException e) {
			e.printStackTrace();
		}

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