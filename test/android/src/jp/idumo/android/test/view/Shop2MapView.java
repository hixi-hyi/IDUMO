package jp.idumo.android.test.view;

import jp.idumo.android.core.exec.AndroidComponent;
import jp.idumo.android.core.exec.AndroidWrapper;
import jp.idumo.android.core.util.AndroidLogger;
import jp.idumo.android.parts.provider.AndroidGPSProvider;
import jp.idumo.android.parts.receiptor.AndroidPinMapViewReceiptor;
import jp.idumo.common.parts.handler.HotpepperHandler;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.util.LogManager;

public class Shop2MapView extends AndroidWrapper {
	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO");
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidGPSProvider idumo0 = new AndroidGPSProvider();
				add(idumo0);
				// NumberProvider idumo0 = new NumberProvider(34.67);
				// add(idumo0);
				// NumberProvider idumo1 = new NumberProvider(135.52);
				// add(idumo1);
				// Number2GPSConverter idumo2 = new Number2GPSConverter();
				// add(idumo2);
				
				HotpepperHandler idumoh = new HotpepperHandler();
				add(idumoh);
				AndroidPinMapViewReceiptor idumor = new AndroidPinMapViewReceiptor();
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
