package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.IDUMOAndroidOrientationData;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.android.sensor.OrientationSensor;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の傾きの情報を取得できるProvider 地磁気センサと加速度センサにより傾きを算出
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class OrientationProvider implements IDUMOSendable, AndroidController {
	
	private OrientationSensor	sensor;
	
	public OrientationProvider(Activity activity) {
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
	public IDUMODataFlowing onCall() {
		IDUMOLogManager.log();
		IDUMODataFlowing p = new IDUMODataFlowing();
		p.add(new IDUMOAndroidOrientationData(sensor.getPitch(), sensor.getRoll(), sensor.getAzmuth()));
		return p;
	}
	
	@Override
	public boolean isReady() {
		return sensor.isReady();
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
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMOAndroidOrientationData.class);
	}
}
