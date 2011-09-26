package com.hixi_hyi.idumo.android.execution.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.AccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class AccelerometerComponent extends AbstractAndroidExecutionComponent {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		SensorManager sensor = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		AccelerometerSensor accelerometerSensor = AccelerometerSensor.INSTANCE;
		accelerometerSensor.init(sensor);
		add(accelerometerSensor);

		AccelerometerProvider accelerometer = new AccelerometerProvider(accelerometerSensor);
		accelerometer.setOption(AccelerometerProvider.Type.X);
		add(accelerometer);

		AccelerometerProvider accelerometer2 = new AccelerometerProvider(accelerometerSensor);
		accelerometer2.setOption(AccelerometerProvider.Type.Y);
		add(accelerometer2);

		AccelerometerProvider accelerometer3 = new AccelerometerProvider(accelerometerSensor);
		accelerometer3.setOption(AccelerometerProvider.Type.Z);
		add(accelerometer3);

		StringConcatHandler concat = new StringConcatHandler("X:");
		add(concat);

		StringConcatHandler concat2 = new StringConcatHandler("Y:");
		add(concat2);

		StringConcatHandler concat3 = new StringConcatHandler("Z:");
		add(concat3);

		TextViewReceiptor textView = new TextViewReceiptor(getActivity());
		add(textView);

		connect(accelerometer, concat);
		connect(accelerometer2, concat2);
		connect(accelerometer3, concat3);

		connect(concat, textView);
		connect(concat2, textView);
		connect(concat3, textView);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);

	}


}
