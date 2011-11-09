package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * 地磁気センサ
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public enum MagneticFieldSensor implements SensorEventListener {

	INSTANCE;

	private SensorManager	sensorManager;
	private Sensor			sensor;
	private int				accurary;
	private float[]			magnet	= new float[3];
	private boolean			isReady;
	private boolean			isInit;

	/**
	 * @return accurary
	 */
	public int getAccurary() {
		return accurary;
	}

	public float[] getMagneticField() {
		return magnet;
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

	public void init(SensorManager sensorManager) {
		isInit = true;
		this.sensorManager = sensorManager;
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
		LogManager.log();
		if (event.sensor.getType() == useSensorType()) {
			magnet = event.values.clone();
			isReady = true;
		}
	}

	public void register() {
		if (sensor == null) {
			LogManager.log();
			sensor = sensorManager.getDefaultSensor(useSensorType());
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
		}
	}

	public void unregister() {
		if (sensor != null) {
			LogManager.log();
			sensor = null;
			sensorManager.unregisterListener(this);
		}
	}

	public int useSensorType() {
		return Sensor.TYPE_MAGNETIC_FIELD;
	}

}
