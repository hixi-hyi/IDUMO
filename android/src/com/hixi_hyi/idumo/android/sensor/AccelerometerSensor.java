package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.ApplicationControllerForAndroid;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * 加速度センサ
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public enum AccelerometerSensor implements SensorEventListener, ApplicationControllerForAndroid {
	
	INSTANCE;
	
	private int				accurary;
	private float[]			accel	= new float[3];
	private Sensor			accelerometer;
	private SensorManager	sensorManager;
	private boolean			isReady;
	
	public void init(SensorManager manager) {
		sensorManager = manager;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		if (sensor.getType() == useSensorType()) {
			this.accurary = accuracy;
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		LogManager.log();
		if (event.sensor.getType() == useSensorType()) {
			accel = event.values.clone();
			isReady = true;
		}
	}
	
	/**
	 * @return accurary
	 */
	public int getAccurary() {
		return accurary;
	}
	
	/**
	 * @return x
	 */
	public float getX() {
		return accel[0];
	}
	
	/**
	 * @return y
	 */
	public float getY() {
		return accel[1];
	}
	
	/**
	 * @return z
	 */
	public float getZ() {
		return accel[2];
	}
	
	public float[] getAccelerometer() {
		return accel;
	}
	
	@Override
	public void onIdumoResume() {
		accelerometer = sensorManager.getDefaultSensor(useSensorType());
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
	}
	
	public int useSensorType() {
		return Sensor.TYPE_ACCELEROMETER;
	}
	
	@Override
	public void onIdumoPause() {
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public void onIdumoDestroy() {}
	
	public boolean isReady() {
		return isReady;
	}
	
}
