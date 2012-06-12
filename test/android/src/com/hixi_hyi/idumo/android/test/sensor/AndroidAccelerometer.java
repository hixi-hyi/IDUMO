package com.hixi_hyi.idumo.android.test.sensor;

import com.hixi_hyi.idumo.android.core.exec.AndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.AndroidWrapper;
import com.hixi_hyi.idumo.android.provider.AndroidAccelerometerProvider;
import com.hixi_hyi.idumo.android.receiptor.AndroidTextViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class AndroidAccelerometer extends AndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidAccelerometerProvider idumo1 = new AndroidAccelerometerProvider(activity);
				add(idumo1);
				
				AndroidTextViewReceiptor idumor = new AndroidTextViewReceiptor(activity);
				add(idumor);
				
				connect(idumo1, idumor);
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(-1);
				setSleepTime(1000);
			}
		});
	}
	
}
