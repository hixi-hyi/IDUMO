package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.IDUMOAndroidLIghtData;
import com.hixi_hyi.idumo.android.sensor.LightSensor;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の光センサの情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class LightProvider implements IDUMOSender, AndroidController {
	
	private LightSensor	light;
	
	public LightProvider(Activity activity) {
		LightSensor lightSensor = LightSensor.INSTANCE;
		if (!lightSensor.isInit()) {
			SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			lightSensor.init(sensor);
		}
		this.light = lightSensor;
	}
	
	@Override
	public IDUMOFlowingData onCall() {
		IDUMOLogManager.log();
		IDUMOFlowingData p = new IDUMOFlowingData();
		p.add(new IDUMOAndroidLIghtData(light.getLight()));
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
	public Class<? extends IDUMOData> sendableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
