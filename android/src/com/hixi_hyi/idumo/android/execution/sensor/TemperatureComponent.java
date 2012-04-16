package com.hixi_hyi.idumo.android.execution.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.TemperatureProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class TemperatureComponent extends AbstractAndroidExecutionComponent {
	
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		
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
