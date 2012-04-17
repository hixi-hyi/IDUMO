package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.sensor.TemperatureSensor;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の温度センサの情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class TemperatureProvider implements IDUMOSender, AndroidController {
	
	private TemperatureSensor	sensor;
	
	public TemperatureProvider(Activity activity) {
		TemperatureSensor temperatureSensor = TemperatureSensor.INSTANCE;
		if (!temperatureSensor.isInit()) {
			SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			temperatureSensor.init(sensor);
		}
		this.sensor = temperatureSensor;
	}
	
	@Override
	public IDUMOFlowingData get() {
		IDUMOLogManager.log();
		IDUMOFlowingData p = new IDUMOFlowingData();
		p.add(sensor.getTemperature());
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
