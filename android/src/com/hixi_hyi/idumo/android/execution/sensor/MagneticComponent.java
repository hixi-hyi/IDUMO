package com.hixi_hyi.idumo.android.execution.sensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.MagneticFiledProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.exec.AbstractExecutionComponent;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class MagneticComponent extends AbstractAndroidExecutionComponent {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		SensorManager sensor = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		MagneticFieldSensor magneticFieldSensor = MagneticFieldSensor.INSTANCE;
		magneticFieldSensor.init(sensor);
		add(magneticFieldSensor);

		MagneticFiledProvider mag1 = new MagneticFiledProvider(magneticFieldSensor);
		mag1.setOption(MagneticFiledProvider.Type.X);
		add(mag1);

		MagneticFiledProvider mag2 = new MagneticFiledProvider(magneticFieldSensor);
		mag2.setOption(MagneticFiledProvider.Type.Y);
		add(mag2);

		MagneticFiledProvider mag3 = new MagneticFiledProvider(magneticFieldSensor);
		mag3.setOption(MagneticFiledProvider.Type.Z);
		add(mag3);

		StringConcatHandler s1 = new StringConcatHandler("X:");
		add(s1);

		StringConcatHandler s2 = new StringConcatHandler("Y:");
		add(s2);

		StringConcatHandler s3 = new StringConcatHandler("Z:");
		add(s3);

		TextViewReceiptor textView = new TextViewReceiptor(getActivity());
		add(textView);

		connect(mag1, s1);
		connect(mag2, s2);
		connect(mag3, s3);

		connect(s1, textView);
		connect(s2, textView);
		connect(s3, textView);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}


}
