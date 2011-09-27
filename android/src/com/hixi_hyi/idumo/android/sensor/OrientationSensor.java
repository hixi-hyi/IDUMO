package com.hixi_hyi.idumo.android.sensor;

import android.hardware.SensorManager;

/**
 * 傾き(地磁気&加速度)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public enum OrientationSensor {
	
	INSTANCE;
	
	private AccelerometerSensor	accel;
	private MagneticFieldSensor	mag;
	
	private float[]				orient		= new float[3];
	
	// 回転行列
	private static final int	MATRIX_SIZE	= 16;
	float[]						inR			= new float[MATRIX_SIZE];
	float[]						outR		= new float[MATRIX_SIZE];
	float[]						I			= new float[MATRIX_SIZE];
	private boolean				isInit;
	
	private void calcOrientation() {
		float[] a = accel.getAccelerometer();
		float[] m = mag.getMagneticField();
		SensorManager.getRotationMatrix(inR, I, a, m);
		// Activityの表示が縦固定の場合。横向きになる場合、修正が必要です
		SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR);
		SensorManager.getOrientation(outR, orient);
	}
	
	public float getAzmuth() {
		calcOrientation();
		return orient[0];
	}
	
	public float[] getOrientation() {
		calcOrientation();
		return orient;
	}
	
	public float getPitch() {
		calcOrientation();
		return orient[1];
	}
	
	public float getRoll() {
		calcOrientation();
		return orient[2];
	}
	
	public void init(AccelerometerSensor accelerometerSensor, MagneticFieldSensor magneticFieldSensor) {
		isInit = true;
		this.accel = accelerometerSensor;
		this.mag = magneticFieldSensor;
	}
	
	public boolean isInit() {
		return isInit;
	}
	
	public boolean isReady() {
		return accel.isReady() && mag.isReady();
	}
	
	public void register() {
		accel.register();
		mag.register();
	}
	
	public void unregister() {
		accel.unregister();
		mag.unregister();
	}
}
