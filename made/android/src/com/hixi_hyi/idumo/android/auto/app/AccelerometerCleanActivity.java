package com.hixi_hyi.idumo.android.auto.app;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidVirtualMachine;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.provider.AccelerometerProvider_Z;
import com.hixi_hyi.idumo.common.handler.ConditionMoreNumberHandler;
import com.hixi_hyi.idumo.common.handler.StringHandler;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
public class AccelerometerCleanActivity extends IDUMOAndroidVirtualMachine {
	@Override
	public void init() {
		setExecutionWithComponent(new AccelerometerCleanComponent());
	}
}
class AccelerometerCleanComponent extends IDUMOAndroidComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
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

