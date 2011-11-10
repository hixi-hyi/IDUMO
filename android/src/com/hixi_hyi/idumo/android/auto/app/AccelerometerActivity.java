package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.*;
import com.hixi_hyi.idumo.android.exec.*;
import com.hixi_hyi.idumo.android.provider.*;
import com.hixi_hyi.idumo.android.handler.*;
import com.hixi_hyi.idumo.android.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.common.provider.*;
import com.hixi_hyi.idumo.common.handler.*;
import com.hixi_hyi.idumo.common.receiptor.*;
public class AccelerometerActivity extends AbstractAndroidActivity {
	@Override
	public void init() {
		setExecutionWithComponent(new AccelerometerComponent());
	}
}
class AccelerometerComponent extends AbstractAndroidExecutionComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		AccelerometerProvider a1 = new AccelerometerProvider(activity);
		a1.setOption(AccelerometerProvider.Type.X);
		add(a1);
		AccelerometerProvider a2 = new AccelerometerProvider(activity);
		a2.setOption(AccelerometerProvider.Type.Y);
		add(a2);
		AccelerometerProvider a3 = new AccelerometerProvider(activity);
		a3.setOption(AccelerometerProvider.Type.Z);
		add(a3);
		StringConcatHandler s1 = new StringConcatHandler("X:");
		add(s1);
		StringConcatHandler s2 = new StringConcatHandler("Y:");
		add(s2);
		StringConcatHandler s3 = new StringConcatHandler("Z:");
		add(s3);
		TextViewReceiptor textView = new TextViewReceiptor(activity);
		add(textView);

		connect(a1, s1);
		connect(a2, s2);
		connect(a3, s3);
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

