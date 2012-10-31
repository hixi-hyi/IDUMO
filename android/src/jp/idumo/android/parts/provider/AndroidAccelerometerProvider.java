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
package jp.idumo.android.parts.provider;

import jp.idumo.android.annotation.IDUMOAndroid;
import jp.idumo.android.component.sensor.AccelerometerSensor;
import jp.idumo.android.core.AndroidActivityController;
import jp.idumo.android.core.AndroidActivityResource;
import jp.idumo.android.core.AndroidController;
import jp.idumo.android.data.AndroidAccelerometerData;
import jp.idumo.android.manifest.AndroidFeature;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.annotation.IDUMOProvider;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.util.LogManager;
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

/**
 * Android上の加速度センサの値を提供するProvider
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
@IDUMOAndroid(features = AndroidFeature.SENSOR_ACCELEROMETOR)
@IDUMOProvider(send = AndroidAccelerometerData.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "加速度センサ", summary = "Androidの加速度センサ")
public class AndroidAccelerometerProvider implements Sendable, AndroidController, AndroidActivityController {
	
	private AccelerometerSensor	accel;
	
	public AndroidAccelerometerProvider() {
		accel = AccelerometerSensor.INSTANCE;
		// lazy initialize (method of registActivity)
	}
	
	@Override
	public boolean isReady() {
		return accel.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		LogManager.log();
		FlowingData p = new FlowingData();
		AndroidAccelerometerData data = new AndroidAccelerometerData(accel.getX(), accel.getY(), accel.getZ());
		p.add(data);
		return p;
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		accel.unregister();
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		accel.register();
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(AndroidAccelerometerData.class);
	}
	
	@Override
	public void registActivity(AndroidActivityResource activity) {
		if (!accel.isInit()) {
			SensorManager sensor = (SensorManager) activity.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
			accel.init(sensor);
		}
	}
	
}
