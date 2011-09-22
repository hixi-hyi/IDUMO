package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * 温度センサ
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public enum TemperatureSensor implements SensorEventListener, AndroidController {
	
	INSTANCE;
	
	private SensorManager	sensorManager;
	private int				accurary;
	private float			temp;
	private boolean			isReady;
	
	/**
	 * @return temp
	 */
	public float getTemperature() {
		return temp;
	}
	
	public void init(SensorManager manager) {
		this.sensorManager = manager;
	}
	
	public int useSensorType() {
		return Sensor.TYPE_TEMPERATURE;
	}
	
	@Override
	public void onIdumoResume() {
		Sensor magnet = sensorManager.getDefaultSensor(useSensorType());
		sensorManager.registerListener(this, magnet, SensorManager.SENSOR_DELAY_UI);
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoPause() {
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public void onIdumoDestroy() {}
	
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
			temp = event.values[0];
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
