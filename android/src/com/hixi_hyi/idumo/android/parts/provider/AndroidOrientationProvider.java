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
package com.hixi_hyi.idumo.android.parts.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.component.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.android.component.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.android.component.sensor.OrientationSensor;
import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.AndroidOrientationData;
import com.hixi_hyi.idumo.core.annotation.IDUMOProvider;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上の傾きの情報を取得できるProvider 地磁気センサと加速度センサにより傾きを算出
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
@IDUMOProvider(author="Hiroyoshi HOUCHI",name="傾きセンサ",send=AndroidOrientationData.class)
public class AndroidOrientationProvider implements Sendable, AndroidController {
	
	private OrientationSensor	sensor;
	
	public AndroidOrientationProvider(Activity activity) {
		OrientationSensor orientationSensor = OrientationSensor.INSTANCE;
		if (!orientationSensor.isInit()) {
			AccelerometerSensor accelerometerSensor = AccelerometerSensor.INSTANCE;
			if (!accelerometerSensor.isInit()) {
				SensorManager sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
				accelerometerSensor.init(sensorManager);
			}
			MagneticFieldSensor magneticFieldSensor = MagneticFieldSensor.INSTANCE;
			if (!magneticFieldSensor.isInit()) {
				SensorManager sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
				magneticFieldSensor.init(sensorManager);
			}
			orientationSensor.init(accelerometerSensor, magneticFieldSensor);
		}
		sensor = orientationSensor;
	}
	
	@Override
	public boolean isReady() {
		return sensor.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		LogManager.log();
		FlowingData p = new FlowingData();
		p.add(new AndroidOrientationData(sensor.getPitch(), sensor.getRoll(), sensor.getAzmuth()));
		return p;
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		sensor.unregister();
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		sensor.register();
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(AndroidOrientationData.class);
	}
}
