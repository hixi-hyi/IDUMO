package com.hixi_hyi.idumo.android.execution.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.OrientationProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.IdumoException;

public class OrientationComponent extends AbstractAndroidExecutionComponent {
	
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		
		OrientationProvider o1 = new OrientationProvider(activity);
		o1.setOption(OrientationProvider.Type.AZMUTH);
		add(o1);
		
		OrientationProvider o2 = new OrientationProvider(activity);
		o2.setOption(OrientationProvider.Type.PITCH);
		add(o2);
		
		OrientationProvider o3 = new OrientationProvider(activity);
		o3.setOption(OrientationProvider.Type.ROLL);
		add(o3);
		
		StringConcatHandler s1 = new StringConcatHandler("Azmuth:");
		add(s1);
		
		StringConcatHandler s2 = new StringConcatHandler("Pitch:");
		add(s2);
		
		StringConcatHandler s3 = new StringConcatHandler("Roll:");
		add(s3);
		
		TextViewReceiptor textView = new TextViewReceiptor(activity);
		add(textView);
		
		connect(o1, s1);
		connect(o2, s2);
		connect(o3, s3);
		
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
