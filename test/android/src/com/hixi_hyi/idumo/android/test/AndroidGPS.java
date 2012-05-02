package com.hixi_hyi.idumo.android.test;

import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidWrapper;
import com.hixi_hyi.idumo.android.provider._AndroidAccelerometerProvider;
import com.hixi_hyi.idumo.android.provider._AndroidGPSProvider;
import com.hixi_hyi.idumo.android.receiptor._TextViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class AndroidGPS extends IDUMOAndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new IDUMOAndroidComponent() {

			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				_AndroidAccelerometerProvider idumo1 = new _AndroidAccelerometerProvider(activity);
//				_AndroidGPSProvider idumo1 = new _AndroidGPSProvider(activity);
				add(idumo1);

				_TextViewReceiptor idumor = new _TextViewReceiptor(activity);
				add(idumor);

				connect(idumo1,idumor);
			}

			@Override
			public void onIdumoPrepare() {
				setLoopCount(-1);
				setSleepTime(1000);
			}
		});
	}

}
