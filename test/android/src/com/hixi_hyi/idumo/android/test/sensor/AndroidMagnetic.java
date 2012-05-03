package com.hixi_hyi.idumo.android.test.sensor;

import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidWrapper;
import com.hixi_hyi.idumo.android.provider.AndroidMagneticFieldProvider;
import com.hixi_hyi.idumo.android.receiptor.AndroidTextViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class AndroidMagnetic extends IDUMOAndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new IDUMOAndroidComponent() {

			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidMagneticFieldProvider idumo1 = new AndroidMagneticFieldProvider(activity);
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
