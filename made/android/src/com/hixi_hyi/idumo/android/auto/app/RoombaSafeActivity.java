package com.hixi_hyi.idumo.android.auto.app;

import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidWrapper;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class RoombaSafeActivity extends IDUMOAndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new RoombaSafeComponent());
	}
	
}

class RoombaSafeComponent extends IDUMOAndroidComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		StringProvider s = new StringProvider("SAFE");
		add(s);
		SendTCPReceiptor r = new SendTCPReceiptor("192.168.11.4", 10000);
		add(r);
		
		connect(s, r);
	}
	
	@Override
	public void onIdumoPrepare() {
		setLoopCount(1);
		setSleepTime(1000);
	}
}

/*
 * <activity android:NAME=".auto.app.RoombaSafeActivity"
 * android:label="RoombaSafe"> <intent-filter> <action
 * android:NAME="android.intent.action.MAIN" /> <category
 * android:NAME="android.intent.category.IDUMO_SAMPLES" /> <category
 * android:NAME="android.intent.category.LAUNCHER" /> </intent-filter>
 * </activity>
 */
