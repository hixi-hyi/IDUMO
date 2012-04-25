package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.AndroidLIghtData;
import com.hixi_hyi.idumo.android.sensor.LightSensor;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の光センサの情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _LightProvider implements IDUMOSendable, AndroidController {
	
	private LightSensor	light;
	
	public _LightProvider(Activity activity) {
		LightSensor lightSensor = LightSensor.INSTANCE;
		if (!lightSensor.isInit()) {
			SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			lightSensor.init(sensor);
		}
		this.light = lightSensor;
	}
	
	@Override
	public IDUMODataFlowing onCall() {
		IDUMOLogManager.log();
		IDUMODataFlowing p = new IDUMODataFlowing();
		p.add(new AndroidLIghtData(light.getLight()));
		return p;
	}
	
	@Override
	public boolean isReady() {
		return light.isReady();
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		light.unregister();
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		light.register();
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(AndroidLIghtData.class);
	}
}
