package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.sensor.LightSensor;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上の光センサの情報を取得できるProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class LightProvider implements Sender,AndroidController {

	private LightSensor	light;

	public LightProvider(Activity activity) {
		SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		LightSensor lightSensor = LightSensor.INSTANCE;
		lightSensor.init(sensor);
		this.light = lightSensor;
	}

	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Float.class);
		return type;
	}

	@Override
	public PipeData getData() {
		LogManager.log();
		PipeData p = new PipeData();
		p.add(light.getLight());
		return p;
	}

	@Override
	public boolean isReady() {
		return light.isReady();
	}

	@Override
	public void onIdumoStart() {
	}

	@Override
	public void onIdumoStop() {
	}

	@Override
	public void onIdumoRestart() {
	}

	@Override
	public void onIdumoResume() {
		light.register();
	}

	@Override
	public void onIdumoPause() {
		light.unregister();
	}

	@Override
	public void onIdumoDestroy() {
	}
}
