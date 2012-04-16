package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * 近接センサ
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public enum ProximitySensor implements SensorEventListener {
	
	INSTANCE;
	
	private SensorManager	sensorManager;
	private Sensor			sensor;
	private int				accurary;
	private float			proximity;
	private boolean			isReady;
	private boolean			isInit;
	
	/**
	 * @return accurary
	 */
	public int getAccurary() {
		return accurary;
	}
	
	/**
	 * @return proximity
	 */
	public float getProximity() {
		return proximity;
	}
	
	public void init(SensorManager manager) {
		isInit = true;
		this.sensorManager = manager;
	}
	
	public boolean isInit() {
		return isInit;
	}
	
	/**
	 * @return isReady
	 */
	public boolean isReady() {
		return isReady;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		if (sensor.getType() == useSensorType()) {
			this.accurary = accuracy;
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		IDUMOLogManager.log();
		if (event.sensor.getType() == useSensorType()) {
			proximity = event.values[0];
			isReady = true;
		}
	}
	
	public void register() {
		if (sensor == null) {
			sensor = sensorManager.getDefaultSensor(useSensorType());
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
		}
	}
	
	public void unregister() {
		if (sensor != null) {
			sensor = null;
			sensorManager.unregisterListener(this);
		}
	}
	
	public int useSensorType() {
		return Sensor.TYPE_PROXIMITY;
	}
	
}
