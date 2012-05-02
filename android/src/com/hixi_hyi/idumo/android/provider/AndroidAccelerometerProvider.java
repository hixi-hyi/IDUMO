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
package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.AndroidAccelerometerData;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の加速度センサの値を提供するProvider
 *
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 *
 */
public class AndroidAccelerometerProvider implements IDUMOSendable, AndroidController {

	private AccelerometerSensor	accel;

	public AndroidAccelerometerProvider(Activity activity) {
		AccelerometerSensor accelerometerSensor = AccelerometerSensor.INSTANCE;
		if (!accelerometerSensor.isInit()) {
			SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			accelerometerSensor.init(sensor);
		}
		this.accel = accelerometerSensor;
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMOLogManager.log();
		IDUMODataFlowing p = new IDUMODataFlowing();
		AndroidAccelerometerData data = new AndroidAccelerometerData(accel.getX(), accel.getY(), accel.getZ());
		p.add(data);
		return p;
	}

	@Override
	public boolean isReady() {
		return accel.isReady();
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
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(AndroidAccelerometerData.class);
	}

}
