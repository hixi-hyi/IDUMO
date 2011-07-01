package com.hixi_hyi.android.provider.imp;

import java.util.ArrayList;
import java.util.Collection;

import android.hardware.SensorManager;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.ProviderEvent;

public class AccelerometerYProvider extends AccelerometerProvider {

	public AccelerometerYProvider(SensorManager manager) {
		super(manager);
	}

	@Override
	public Collection<Class<?>> getNotifyDataType() {
		ArrayList<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Float.class);
		return type;
	}

	@Override
	public void providerChanged() {
		PipeData p = new PipeData();
		p.add(accel[1]);
		super.dataChange(new ProviderEvent(p));
	}
}
