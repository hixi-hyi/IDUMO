package com.hixi_hyi.idumo.android.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 加速度センサ
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public enum AccelerometerSensor implements SensorEventListener {

	INSTANCE;

	private int				accurary;
	private float[]			accel	= new float[3];
	private Sensor			sensor;
	private SensorManager	sensorManager;
	private boolean			isReady;
	private boolean			isInit;

	public float[] getAccelerometer() {
		return accel;
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

	public void init(SensorManager manager) {
		isInit = true;
		sensorManager = manager;
	}

	public boolean isInit() {
		return isInit;
	}

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
//		LogManager.log();
		if (event.sensor.getType() == useSensorType()) {
			accel = event.values.clone();
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
		return Sensor.TYPE_ACCELEROMETER;
	}

}
