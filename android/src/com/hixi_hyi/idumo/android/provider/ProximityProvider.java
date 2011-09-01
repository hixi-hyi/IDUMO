package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.android.provider.GPSProvider.Type;
import com.hixi_hyi.idumo.android.sensor.ProximitySensor;
import com.hixi_hyi.idumo.android.util.LogUtil;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
/**
 * Android上の近接センサの情報を取得できるProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class ProximityProvider implements Sender{

	private ProximitySensor proximity;

	public ProximityProvider(ProximitySensor proximitySensor) {
		this.proximity = proximitySensor;
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
		p.add(proximity.getProximity());
		return p;
	}

	@Override
	public boolean isReady() {
		return proximity.isReady();
	}

}
