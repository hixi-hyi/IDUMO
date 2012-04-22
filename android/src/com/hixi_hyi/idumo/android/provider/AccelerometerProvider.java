package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.IDUMOAndroidAccelerometerData;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の加速度センサの値を提供するProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class AccelerometerProvider implements IDUMOSendable, AndroidController {
	
	private AccelerometerSensor	accel;
	
	public AccelerometerProvider(Activity activity) {
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
		IDUMOAndroidAccelerometerData data = new IDUMOAndroidAccelerometerData(accel.getX(), accel.getY(), accel.getZ());
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
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOAndroidAccelerometerData.class);
	}
	
}
