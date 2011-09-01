package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.util.LogUtil;

/**
 * 地磁気センサ
 * @author Hiroyoshi HOUCHI
 *
 */
public enum MagneticFieldSensor implements SensorEventListener,ApplicationControlforAndroid{

	INSTANCE;

	private SensorManager sensorManager;
	private int accurary;
	private float[] magnet = new float[3];
	private boolean isReady;

	public void init(SensorManager sensorManager){
		this.sensorManager = sensorManager;
	}

	public int useSensorType() {
		return Sensor.TYPE_MAGNETIC_FIELD;
	}

	@Override
	public void onResume() {
		Sensor magnet = sensorManager.getDefaultSensor(useSensorType());
		sensorManager.registerListener(this, magnet ,SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onRestart() {
	}

	@Override
	public void onPause() {
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onStop() {
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		if (sensor.getType() == useSensorType()) {
			this.accurary = accuracy;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		LogUtil.d();
		if(event.sensor.getType()==useSensorType()){
			magnet = event.values.clone();
//			x = event.values[0];
//			y = event.values[1];
//			z = event.values[2];
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
		return magnet[0];
	}
	/**
	 * @return y
	 */
	public float getY() {
		return magnet[1];
	}
	/**
	 * @return z
	 */
	public float getZ() {
		return magnet[2];
	}
	public float[] getMagneticField(){
		return magnet;
	}
	/**
	 * @return isReady
	 */
	public boolean isReady() {
		return isReady;
	}

}