package com.hixi_hyi.idumo.android.test.view;

import com.hixi_hyi.idumo.android.core.exec.AndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.AndroidWrapper;
import com.hixi_hyi.idumo.android.core.util.AndroidLogger;
import com.hixi_hyi.idumo.android.receiptor.AndroidMapViewReceiptor;
import com.hixi_hyi.idumo.common.converter.Number2GPSConverter;
import com.hixi_hyi.idumo.common.provider.NumberProvider;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Number2MapView extends AndroidWrapper {
	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO");
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				NumberProvider idumo0 = new NumberProvider(35.681099);
				add(idumo0);
				NumberProvider idumo1 = new NumberProvider(139.767084);
				add(idumo1);
				Number2GPSConverter idumo2 = new Number2GPSConverter();
				add(idumo2);
				
				AndroidMapViewReceiptor idumor = new AndroidMapViewReceiptor(activity);
				add(idumor);
				
				connect(idumo0, idumo2);
				connect(idumo1, idumo2);
				connect(idumo2, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(-1);
				setSleepTime(1000);
			}
		});
	}
	
}
