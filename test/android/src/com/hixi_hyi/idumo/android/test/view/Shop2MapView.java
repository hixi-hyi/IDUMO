package com.hixi_hyi.idumo.android.test.view;

import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidWrapper;
import com.hixi_hyi.idumo.android.core.util.IDUMOAndroidLogger;
import com.hixi_hyi.idumo.android.provider.AndroidAccelerometerProvider;
import com.hixi_hyi.idumo.android.provider.AndroidGPSProvider;
import com.hixi_hyi.idumo.android.receiptor.AndroidMapViewReceiptor;
import com.hixi_hyi.idumo.android.receiptor.AndroidPinMapViewReceiptor;
import com.hixi_hyi.idumo.android.receiptor.AndroidTextViewReceiptor;
import com.hixi_hyi.idumo.common.converter.Number2GPSConverter;
import com.hixi_hyi.idumo.common.provider.HotpepperHandler;
import com.hixi_hyi.idumo.common.provider.NumberProvider;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class Shop2MapView extends IDUMOAndroidWrapper {
	@Override
	public void init() {
		IDUMOLogManager.DEBUG = true;
		IDUMOLogManager.LOGGER = new IDUMOAndroidLogger("IDUMO");
		setExecutionWithComponent(new IDUMOAndroidComponent() {

			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidGPSProvider idumo0 = new AndroidGPSProvider(activity);
				add(idumo0);
//				NumberProvider idumo0 = new NumberProvider(34.67);
//				add(idumo0);
//				NumberProvider idumo1 = new NumberProvider(135.52);
//				add(idumo1);
//				Number2GPSConverter idumo2 = new Number2GPSConverter();
//				add(idumo2);
	
				HotpepperHandler idumoh = new HotpepperHandler();
				add(idumoh);
				AndroidPinMapViewReceiptor idumor = new AndroidPinMapViewReceiptor(activity);
				add(idumor);
				
				
//				connect(idumo0,idumo2);
//				connect(idumo1,idumo2);
//				connect(idumo2,idumoh);
				connect(idumo0,idumoh);
				connect(idumoh,idumor);
				

			}

			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}

}
