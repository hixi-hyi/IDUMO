package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.android.sensor.LightSensor;
import com.hixi_hyi.idumo.android.util.LogUtil;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;

/**
 * Android上の光センサの情報を取得できるProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class LightProvider implements Sender{


	private LightSensor light;

	public LightProvider(LightSensor lightSensor) {
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
		LogUtil.d();
		PipeData p = new PipeData();
		p.add(light.getLight());
		return p;
	}

	@Override
	public boolean isReady() {
		return light.isReady();
	}
}
