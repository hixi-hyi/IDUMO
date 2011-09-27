package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.core.IdumoComponent;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * 温度センサ
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public enum TemperatureSensor implements SensorEventListener {

	INSTANCE;

	private SensorManager	sensorManager;
	private Sensor sensor;
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

	public void register() {
		if(sensor==null){
			sensor = sensorManager.getDefaultSensor(useSensorType());
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
		}
	}

	public void unregister() {
		if(sensor!=null){
			sensor=null;
			sensorManager.unregisterListener(this);
		}
	}


}
