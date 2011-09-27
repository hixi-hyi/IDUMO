package com.hixi_hyi.idumo.android.execution.sensor;

import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.TemperatureProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.TemperatureSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;

public class TemperatureComponent extends AbstractAndroidExecutionComponent {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {

		TemperatureProvider provider = new TemperatureProvider(activity);
		add(provider);

		StringConcatHandler s = new StringConcatHandler("Temp");
		add(s);

		TextViewReceiptor textView = new TextViewReceiptor(activity);
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
