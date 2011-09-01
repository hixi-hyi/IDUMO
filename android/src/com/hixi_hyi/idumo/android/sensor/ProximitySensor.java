package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * 近接センサ
 * @author Hiroyoshi HOUCHI
 *
 */
public enum ProximitySensor implements SensorEventListener,
		ApplicationControlforAndroid {

	INSTANCE;

	private SensorManager sensorManager;
	private int accurary;
	private float proximity;
	private boolean isReady;

	/**
	 * @return proximity
	 */
	public float getProximity() {
		return proximity;
	}

	public void init(SensorManager manager){
		this.sensorManager = manager;
	}
	public int useSensorType() {
		return Sensor.TYPE_PROXIMITY;
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
		LogManager.log();
		if(event.sensor.getType()==useSensorType()){
			proximity = event.values[0];
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
