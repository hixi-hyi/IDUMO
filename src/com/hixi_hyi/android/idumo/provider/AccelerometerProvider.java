package com.hixi_hyi.android.idumo.provider;

import java.util.ArrayList;
import java.util.Collection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.android.idumo.AndroidInterface;
import com.hixi_hyi.android.idumo.ProviderInterface;
import com.hixi_hyi.android.idumo.data.PipeData;

public class AccelerometerProvider implements ProviderInterface , SensorEventListener ,AndroidInterface{

	protected int accelerometerAccurary;
	protected float accel[];
	protected Sensor accelerometer;
	protected SensorManager sensorManager;
	protected boolean isReady;

	public AccelerometerProvider(SensorManager manager) {
		accel = new float[3];
		this.sensorManager = manager;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		if (sensor.getType() == useSensorType()) {
			accelerometerAccurary = accuracy;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == useSensorType()) {
			for (int i = 0; i < 3; i++) {
				accel[i] = event.values[i];
			}
			isReady = true;
		}
	}

	@Override
	public void onResume() {
		accelerometer = sensorManager.getDefaultSensor(useSensorType());
		sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_UI);
	}

	public int useSensorType() {
		return Sensor.TYPE_ACCELEROMETER;
	}

	@Override
	public void onPause() {
		sensorManager.unregisterListener(this);
	}

	public void providerChanged() {
		PipeData p = new PipeData();
		for (float f : accel) {
			p.add(f);
		}
	}

	@Override
	public Collection<Class<?>> getNotifyDataType() {
		ArrayList<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Float.class);
		type.add(Float.class);
		type.add(Float.class);
		return type;
	}

	@Override
	public PipeData getData() {
		PipeData p = new PipeData();
		p.add(accel[0]);
		p.add(accel[1]);
		p.add(accel[2]);
		return p;
	}

	@Override
	public void setParameter(PipeData parameter) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void onStart() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onRestart() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStop() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean isReady() {
		return isReady;
	}

}
