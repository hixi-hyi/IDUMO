package com.hixi_hyi.idumo.android.execution.sensor;

import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.ProximityProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.ProximitySensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;

public class ProximityComponent extends AbstractAndroidExecutionComponent {
	
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		ProximitySensor proximitySensor = ProximitySensor.INSTANCE;
		proximitySensor.init(sensor);
		add(proximitySensor);
		
		ProximityProvider prom = new ProximityProvider(proximitySensor);
		add(prom);
		
		StringConcatHandler s1 = new StringConcatHandler("Proximity:");
		add(s1);
		
		TextViewReceiptor textView = new TextViewReceiptor(activity);
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
