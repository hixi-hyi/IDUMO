package jp.idumo.android.test.view;

import jp.idumo.android.core.exec.AndroidComponent;
import jp.idumo.android.core.exec.AndroidWrapper;
import jp.idumo.android.core.util.AndroidLogger;
import jp.idumo.android.parts.receiptor.AndroidMapViewReceiptor;
import jp.idumo.common.parts.adapter.Number2GPSAdapter;
import jp.idumo.common.parts.provider.NumberProvider;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.util.LogManager;

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
				Number2GPSAdapter idumo2 = new Number2GPSAdapter();
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
