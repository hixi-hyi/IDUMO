package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.util.LogUtil;

/**
 * 光センサ
 * @author Hiroyoshi HOUCHI
 *
 */
public enum LightSensor implements SensorEventListener,
		ApplicationControlforAndroid {

	INSTANCE;

	private SensorManager sensorManager;
	private int accurary;
	private float light;
	private boolean isReady;

	/**
	 * @return light
	 */
	public float getLight() {
		return light;
	}

	public void init(SensorManager sensorManager){
		this.sensorManager = sensorManager;
	}

	public int useSensorType() {
		return Sensor.TYPE_LIGHT;
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
			light = event.values[0];
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
	 * @return isReady
	 */
	public boolean isReady() {
		return isReady;
	}


}
