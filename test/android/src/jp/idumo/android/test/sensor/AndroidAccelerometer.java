package jp.idumo.android.test.sensor;

import jp.idumo.android.core.exec.AndroidComponent;
import jp.idumo.android.core.exec.AndroidWrapper;
import jp.idumo.android.parts.provider.AndroidAccelerometerProvider;
import jp.idumo.android.parts.receiptor.AndroidTextViewReceiptor;
import jp.idumo.core.exception.IDUMOException;

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
