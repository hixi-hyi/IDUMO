package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.android.sensor.OrientationSensor;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上の傾きの情報を取得できるProvider 地磁気センサと加速度センサにより傾きを算出
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class OrientationProvider_Azmuth implements IDUMOSender, AndroidController {
	
	private OrientationSensor	sensor;
	
	public OrientationProvider_Azmuth(Activity activity) {
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
	public PipeData getData() {
		LogManager.log();
		PipeData p = new PipeData();
		p.add(sensor.getAzmuth());
		return p;
	}
	
	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Float.class);
		return type;
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
	
}
