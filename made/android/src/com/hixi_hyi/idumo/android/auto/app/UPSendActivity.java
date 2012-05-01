package com.hixi_hyi.idumo.android.auto.app;

import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidWrapper;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class UPSendActivity extends IDUMOAndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new UPSendComponent());
	}
	
}

class UPSendComponent extends IDUMOAndroidComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		IDUMOLogManager.log();
		StringProvider s = new StringProvider("CLEAN");
		add(s);
		IDUMOLogManager.debug("string ok");
		SendTCPReceiptor r = new SendTCPReceiptor("192.168.12.4", 10000);
		add(r);
		IDUMOLogManager.debug("tcp ok");
		connect(s, r);
		IDUMOLogManager.debug("connection ok");
	}
	
	@Override
	public void onIdumoPrepare() {
		setLoopCount(1);
		setSleepTime(1000);
	}
}

/*
 * <activity android:NAME=".auto.app.UPSendActivity" android:label="UPSend">
 * <intent-filter> <action android:NAME="android.intent.action.MAIN" />
 * <category android:NAME="android.intent.category.IDUMO_SAMPLES" /> <category
 * android:NAME="android.intent.category.LAUNCHER" /> </intent-filter>
 * </activity>
 */
