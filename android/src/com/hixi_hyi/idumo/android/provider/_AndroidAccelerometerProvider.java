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
 * 
 */
public class _AndroidAccelerometerProvider implements IDUMOSendable, AndroidController {
	
	private AccelerometerSensor	accel;
	
	public _AndroidAccelerometerProvider(Activity activity) {
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
