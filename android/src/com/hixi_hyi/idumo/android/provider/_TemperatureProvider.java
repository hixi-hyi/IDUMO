package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.AndroidTemperatureData;
import com.hixi_hyi.idumo.android.sensor.TemperatureSensor;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の温度センサの情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _TemperatureProvider implements IDUMOSendable, AndroidController {
	
	private TemperatureSensor	sensor;
	
	public _TemperatureProvider(Activity activity) {
		TemperatureSensor temperatureSensor = TemperatureSensor.INSTANCE;
		if (!temperatureSensor.isInit()) {
			SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			temperatureSensor.init(sensor);
		}
		this.sensor = temperatureSensor;
	}
	
	@Override
	public FlowingData onCall() {
		IDUMOLogManager.log();
		FlowingData p = new FlowingData();
		p.add(new AndroidTemperatureData(sensor.getTemperature()));
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
	public ConnectDataType sendableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
