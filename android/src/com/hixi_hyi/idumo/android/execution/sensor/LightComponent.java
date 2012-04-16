package com.hixi_hyi.idumo.android.execution.sensor;

import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.provider.LightProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class LightComponent extends IDUMOAndroidComponent {
	
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
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
