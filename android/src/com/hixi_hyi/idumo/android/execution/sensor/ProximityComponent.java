package com.hixi_hyi.idumo.android.execution.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.ProximityProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class ProximityComponent extends AbstractAndroidExecutionComponent {
	
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		
		ProximityProvider prom = new ProximityProvider(activity);
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
