package com.hixi_hyi.idumo.android.test.sensor;

import com.hixi_hyi.idumo.android.core.exec.AndroidComponent;
import com.hixi_hyi.idumo.android.core.exec.AndroidWrapper;
import com.hixi_hyi.idumo.android.parts.provider.AndroidProximityProvider;
import com.hixi_hyi.idumo.android.parts.receiptor.AndroidTextViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class AndroidProximity extends AndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidProximityProvider idumo1 = new AndroidProximityProvider(activity);
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
