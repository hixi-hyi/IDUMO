package com.hixi_hyi.idumo.android.activity.app;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.common.handler.ReversedGeocordingHandler;

public class ReverseGeoLocationActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new ReverseGeoLocationComponent());
	}

	public class ReverseGeoLocationComponent extends AbstractAndroidExecutionComponent {

		@Override
		public void onIdumoMakeFlowChart() throws IdumoException {

			GPSProvider gps1 = new GPSProvider(activity);
			gps1.setOption(GPSProvider.Type.LATITUDE);
			add(gps1);

			GPSProvider gps2 = new GPSProvider(activity);
			gps2.setOption(GPSProvider.Type.LONGITUDE);
			add(gps2);

			ReversedGeocordingHandler rgh = new ReversedGeocordingHandler();
			add(rgh);

			TextViewReceiptor textview = new TextViewReceiptor(activity);
			add(textview);

			connect(gps1, rgh);
			connect(gps2, rgh);
			connect(rgh, textview);
		}

		@Override
		public void onIdumoPrepare() {
			setLoopCount(-1);
			setSleepTime(1000);
		}

	}
}
