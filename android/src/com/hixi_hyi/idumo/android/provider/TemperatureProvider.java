package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.sensor.TemperatureSensor;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上の温度センサの情報を取得できるProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class TemperatureProvider implements Sender,AndroidController {

	private TemperatureSensor	sensor;

	public TemperatureProvider(Activity activity) {
		SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		TemperatureSensor temperatureSensor = TemperatureSensor.INSTANCE;
		temperatureSensor.init(sensor);
		this.sensor = temperatureSensor;
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
		p.add(sensor.getTemperature());
		return p;
	}

	@Override
	public boolean isReady() {
		return sensor.isReady();
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
		sensor.register();
	}

	@Override
	public void onIdumoPause() {
		sensor.unregister();
	}

	@Override
	public void onIdumoDestroy() {
	}

}
