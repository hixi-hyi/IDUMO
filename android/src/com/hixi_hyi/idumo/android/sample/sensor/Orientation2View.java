package com.hixi_hyi.idumo.android.sample.sensor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.OrientationProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.android.sensor.OrientationSensor;
import com.hixi_hyi.idumo.android.util.LogUtil;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

public class Orientation2View extends Activity implements Runnable {

	private ArrayList<ApplicationControlforAndroid> android;
	private SensorManager sensorManager;
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

		sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		AccelerometerSensor accelerometerSensor = AccelerometerSensor.INSTANCE;
		accelerometerSensor.init(sensorManager);
		android.add(accelerometerSensor);
		MagneticFieldSensor magneticFieldSensor = MagneticFieldSensor.INSTANCE;
		magneticFieldSensor.init(sensorManager);
		android.add(magneticFieldSensor);
		OrientationSensor orientationSensor = OrientationSensor.INSTANCE;
		orientationSensor.init(accelerometerSensor, magneticFieldSensor);

		OrientationProvider o1 = new OrientationProvider(orientationSensor);
		OrientationProvider o2 = new OrientationProvider(orientationSensor);
		OrientationProvider o3 = new OrientationProvider(orientationSensor);

		try {
			o1.setOption(OrientationProvider.Type.AZMUTH);
			o2.setOption(OrientationProvider.Type.PITCH);
			o3.setOption(OrientationProvider.Type.ROLL);
		} catch (IdumoException e) {
			e.printStackTrace();
		}

		StringConcatHandler s1 = new StringConcatHandler("Azmuth:");
		StringConcatHandler s2 = new StringConcatHandler("Pitch:");
		StringConcatHandler s3 = new StringConcatHandler("Roll:");

		s1.setSender(o1);
		s2.setSender(o2);
		s3.setSender(o3);

		textView = new TextViewReceiptor(this);

		textView.setSender(s1,s2,s3);


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