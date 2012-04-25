/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 * 
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
		// IDUMOLogManager.log();
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
