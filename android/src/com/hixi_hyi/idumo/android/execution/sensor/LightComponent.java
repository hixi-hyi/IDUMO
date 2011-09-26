package com.hixi_hyi.idumo.android.execution.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.LightProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.LightSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class LightComponent extends AbstractAndroidExecutionComponent {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		SensorManager sensor = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		LightSensor lightSensor = LightSensor.INSTANCE;
		lightSensor.init(sensor);
		add(lightSensor);

		LightProvider light = new LightProvider(lightSensor);
		add(light);

		StringConcatHandler s1 = new StringConcatHandler("Light:");
		add(s1);

		TextViewReceiptor textView = new TextViewReceiptor(getActivity());
		add(textView);

		connect(light, s1);
		connect(s1, textView);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}
