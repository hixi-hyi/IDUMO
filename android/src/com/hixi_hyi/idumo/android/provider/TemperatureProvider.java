package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.android.sensor.TemperatureSensor;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上の温度センサの情報を取得できるProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class TemperatureProvider implements Sender{

	private TemperatureSensor sensor;

	public TemperatureProvider(TemperatureSensor temperatureSensor) {
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

}
