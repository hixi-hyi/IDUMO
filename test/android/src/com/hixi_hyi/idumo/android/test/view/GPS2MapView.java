package com.hixi_hyi.idumo.android.test.view;

import com.hixi_hyi.idumo.android.core.exec.AndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.AndroidWrapper;
import com.hixi_hyi.idumo.android.core.util.AndroidLogger;
import com.hixi_hyi.idumo.android.provider.AndroidGPSProvider;
import com.hixi_hyi.idumo.android.receiptor.AndroidMapViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;

public class GPS2MapView extends AndroidWrapper {
	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO");
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidGPSProvider idumo0 = new AndroidGPSProvider(activity);
				add(idumo0);
				AndroidMapViewReceiptor idumor = new AndroidMapViewReceiptor(activity);
				add(idumor);
				
				connect(idumo0, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(-1);
				setSleepTime(1000);
			}
		});
	}
	
}
