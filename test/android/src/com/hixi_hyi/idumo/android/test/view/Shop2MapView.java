package com.hixi_hyi.idumo.android.test.view;

import com.hixi_hyi.idumo.android.core.exec.AndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.AndroidWrapper;
import com.hixi_hyi.idumo.android.core.util.AndroidLogger;
import com.hixi_hyi.idumo.android.parts.provider.AndroidGPSProvider;
import com.hixi_hyi.idumo.android.parts.receiptor.AndroidPinMapViewReceiptor;
import com.hixi_hyi.idumo.common.parts.handler.HotpepperHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Shop2MapView extends AndroidWrapper {
	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO");
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidGPSProvider idumo0 = new AndroidGPSProvider(activity);
				add(idumo0);
				// NumberProvider idumo0 = new NumberProvider(34.67);
				// add(idumo0);
				// NumberProvider idumo1 = new NumberProvider(135.52);
				// add(idumo1);
				// Number2GPSConverter idumo2 = new Number2GPSConverter();
				// add(idumo2);
				
				HotpepperHandler idumoh = new HotpepperHandler();
				add(idumoh);
				AndroidPinMapViewReceiptor idumor = new AndroidPinMapViewReceiptor(activity);
				add(idumor);
				
				// connect(idumo0,idumo2);
				// connect(idumo1,idumo2);
				// connect(idumo2,idumoh);
				connect(idumo0, idumoh);
				connect(idumoh, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
	
}
