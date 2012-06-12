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
		accel = accelerometerSensor;
		mag = magneticFieldSensor;
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
