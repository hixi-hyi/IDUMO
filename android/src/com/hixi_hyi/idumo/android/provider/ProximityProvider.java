package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.IDUMOAndroidProximityData;
import com.hixi_hyi.idumo.android.sensor.ProximitySensor;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の近接センサの情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class ProximityProvider implements IDUMOSendable, AndroidController {
	
	private ProximitySensor	proximity;
	
	public ProximityProvider(Activity activity) {
		ProximitySensor proximitySensor = ProximitySensor.INSTANCE;
		if (!proximitySensor.isInit()) {
			SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			proximitySensor.init(sensor);
		}
		this.proximity = proximitySensor;
	}
	
	@Override
	public IDUMODataFlowing onCall() {
		IDUMOLogManager.log();
		IDUMODataFlowing p = new IDUMODataFlowing();
		p.add(new IDUMOAndroidProximityData(proximity.getProximity()));
		return p;
	}
	
	@Override
	public boolean isReady() {
		return proximity.isReady();
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		proximity.unregister();
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		proximity.register();
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMOAndroidProximityData.class);
	}
	
}
