package jp.idumo.android.test.sensor;

import jp.idumo.android.core.exec.AndroidComponent;
import jp.idumo.android.core.exec.AndroidWrapper;
import jp.idumo.android.parts.provider.AndroidMagneticFieldProvider;
import jp.idumo.android.parts.receiptor.AndroidTextViewReceiptor;
import jp.idumo.core.exception.IDUMOException;

public class AndroidMagnetic extends AndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidMagneticFieldProvider idumo1 = new AndroidMagneticFieldProvider();
				add(idumo1);
				
				AndroidTextViewReceiptor idumor = new AndroidTextViewReceiptor();
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
