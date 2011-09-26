package com.hixi_hyi.idumo.android.execution.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.ProximityProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.ProximitySensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class ProximityComponent extends AbstractAndroidExecutionComponent {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		SensorManager sensor = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		ProximitySensor proximitySensor = ProximitySensor.INSTANCE;
		proximitySensor.init(sensor);
		add(proximitySensor);

		ProximityProvider prom = new ProximityProvider(proximitySensor);
		add(prom);

		StringConcatHandler s1 = new StringConcatHandler("Proximity:");
		add(s1);

		TextViewReceiptor textView = new TextViewReceiptor(getActivity());
		add(textView);

		connect(prom, s1);
		connect(s1, textView);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}


}
