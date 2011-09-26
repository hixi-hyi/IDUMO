package com.hixi_hyi.idumo.android.execution.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.provider.TemperatureProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.TemperatureSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class TemperatureComponent extends AbstractAndroidExecutionComponent {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		SensorManager sensor = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		TemperatureSensor temperatureSensor = TemperatureSensor.INSTANCE;
		temperatureSensor.init(sensor);
		add(temperatureSensor);

		TemperatureProvider provider = new TemperatureProvider(temperatureSensor);
		add(provider);

		StringConcatHandler s = new StringConcatHandler("Temp");
		add(s);

		TextViewReceiptor textView = new TextViewReceiptor(getActivity());
		add(textView);

		connect(provider, s);
		connect(s, textView);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}


}
