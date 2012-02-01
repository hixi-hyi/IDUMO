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
public class AccelerometerCleanActivity extends AbstractAndroidActivity {
	@Override
	public void init() {
		setExecutionWithComponent(new AccelerometerCleanComponent());
	}
}
class AccelerometerCleanComponent extends AbstractAndroidExecutionComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		AccelerometerProvider_Z a = new AccelerometerProvider_Z(activity);
		add(a);
		ConditionMoreNumberHandler h = new ConditionMoreNumberHandler(0.0f);
		add(h);
		StringHandler s = new StringHandler("RECORD");
		add(s);
		SendTCPReceiptor r = new SendTCPReceiptor("192.168.11.13",10000);
		add(r);

		connect(a, h);
		connect(h, s);
		connect(s, r);
	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}

