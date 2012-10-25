package jp.idumo.android.test.web;

import jp.idumo.android.core.exec.AndroidComponent;
import jp.idumo.android.core.exec.AndroidWrapper;
import jp.idumo.android.core.util.AndroidLogger;
import jp.idumo.android.parts.provider.AndroidGPSProvider;
import jp.idumo.android.parts.receiptor.AndroidMapViewReceiptor;
import jp.idumo.android.parts.receiptor.AndroidTextViewReceiptor;
import jp.idumo.common.parts.provider.LivedoorWeatherProvider;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.util.LogManager;

public class LivedoorWeather extends AndroidWrapper {
	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO");
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				LivedoorWeatherProvider idumo0 = new LivedoorWeatherProvider(63); 
				add(idumo0);
				AndroidTextViewReceiptor idumor = new AndroidTextViewReceiptor();
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
