package com.hixi_hyi.idumo.android.test.sensor;

import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidWrapper;
import com.hixi_hyi.idumo.android.provider.AndroidAccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.AndroidTextViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class AndroidAccelerometer extends IDUMOAndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new IDUMOAndroidComponent() {

			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidAccelerometerProvider idumo1 = new AndroidAccelerometerProvider(activity);
				add(idumo1);

				AndroidTextViewReceiptor idumor = new AndroidTextViewReceiptor(activity);
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
