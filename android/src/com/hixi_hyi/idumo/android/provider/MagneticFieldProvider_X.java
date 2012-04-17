package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の地磁気センサの情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class MagneticFieldProvider_X implements IDUMOSender, AndroidController {
	
	private MagneticFieldSensor	magnet;
	
	public MagneticFieldProvider_X(Activity activity) {
		MagneticFieldSensor magneticFieldSensor = MagneticFieldSensor.INSTANCE;
		if (!magneticFieldSensor.isInit()) {
			SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			magneticFieldSensor.init(sensor);
		}
		this.magnet = magneticFieldSensor;
	}
	
	@Override
	public IDUMOFlowingData get() {
		IDUMOLogManager.log();
		IDUMOFlowingData p = new IDUMOFlowingData();
		p.add(magnet.getX());
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
		return magnet.isReady();
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		magnet.unregister();
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		magnet.register();
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
}
