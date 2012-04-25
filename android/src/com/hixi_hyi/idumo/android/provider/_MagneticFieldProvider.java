package com.hixi_hyi.idumo.android.provider;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.android.data.AndroidMagneticFieldData;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上の地磁気センサの情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _MagneticFieldProvider implements IDUMOSendable, AndroidController {
	
	private MagneticFieldSensor	magnet;
	
	public _MagneticFieldProvider(Activity activity) {
		MagneticFieldSensor magneticFieldSensor = MagneticFieldSensor.INSTANCE;
		if (!magneticFieldSensor.isInit()) {
			SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			magneticFieldSensor.init(sensor);
		}
		this.magnet = magneticFieldSensor;
	}
	
	@Override
	public IDUMODataFlowing onCall() {
		IDUMOLogManager.log();
		IDUMODataFlowing p = new IDUMODataFlowing();
		AndroidMagneticFieldData data = new AndroidMagneticFieldData(magnet.getX(), magnet.getY(), magnet.getZ());
		p.add(data);
		return p;
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
	
	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(AndroidMagneticFieldData.class);
	}
	
}
