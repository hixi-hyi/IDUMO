package com.hixi_hyi.idumo.android.execution.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.LightProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;

public class LightComponent extends AbstractAndroidExecutionComponent {
	
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		LightProvider light = new LightProvider(activity);
		add(light);
		
		StringConcatHandler s1 = new StringConcatHandler("Light:");
		add(s1);
		
		TextViewReceiptor textView = new TextViewReceiptor(activity);
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
