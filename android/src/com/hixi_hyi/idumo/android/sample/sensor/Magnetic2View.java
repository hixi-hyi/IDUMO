package com.hixi_hyi.idumo.android.sample.sensor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.MagneticFiledProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.android.util.LogUtil;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

public class Magnetic2View extends Activity implements Runnable {

	private ArrayList<ApplicationControlforAndroid> android;
	private MagneticFiledProvider mag;
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
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<ApplicationControlforAndroid>();
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
