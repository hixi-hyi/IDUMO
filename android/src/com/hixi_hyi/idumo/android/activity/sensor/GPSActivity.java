package com.hixi_hyi.idumo.android.activity.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class GPSActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new GPS2Component());
	}

	public class GPS2Component extends AbstractAndroidExecutionComponent {

		@Override
		public void onIdumoMakeFlowChart() throws IDUMOException {

			GPSProvider gps = new GPSProvider(activity);
			gps.setOption(GPSProvider.Type.ACCURARY);
			add(gps);

			GPSProvider gps2 = new GPSProvider(activity);
			gps2.setOption(GPSProvider.Type.ALTITUDE);
			add(gps2);

			GPSProvider gps3 = new GPSProvider(activity);
			gps3.setOption(GPSProvider.Type.BEARING);
			add(gps3);

			GPSProvider gps4 = new GPSProvider(activity);
			gps4.setOption(GPSProvider.Type.LATITUDE);
			add(gps4);

			GPSProvider gps5 = new GPSProvider(activity);
			gps5.setOption(GPSProvider.Type.LONGITUDE);
			add(gps5);

			GPSProvider gps6 = new GPSProvider(activity);
			gps6.setOption(GPSProvider.Type.SPEED);
			add(gps6);

			GPSProvider gps7 = new GPSProvider(activity);
			gps7.setOption(GPSProvider.Type.TIME);
			add(gps7);

			StringConcatHandler s1 = new StringConcatHandler("Accuary:");
			add(s1);

			StringConcatHandler s2 = new StringConcatHandler("Altitude:");
			add(s2);

			StringConcatHandler s3 = new StringConcatHandler("Bearing:");
			add(s3);

			StringConcatHandler s4 = new StringConcatHandler("Latitude:");
			add(s4);

			StringConcatHandler s5 = new StringConcatHandler("Longitude:");
			add(s5);

			StringConcatHandler s6 = new StringConcatHandler("Speed:");
			add(s6);

			StringConcatHandler s7 = new StringConcatHandler("Time:");
			add(s7);

			TextViewReceiptor textView = new TextViewReceiptor(activity);
			add(textView);

			connect(gps, s1);
			connect(gps2, s2);
			connect(gps3, s3);
			connect(gps4, s4);
			connect(gps5, s5);
			connect(gps6, s6);
			connect(gps7, s7);

			connect(s1, textView);
			connect(s2, textView);
			connect(s3, textView);
			connect(s4, textView);
			connect(s5, textView);
			connect(s6, textView);
			connect(s7, textView);

		}

		@Override
		public void onIdumoPrepare() {
			setLoopCount(-1);
			setSleepTime(1000);
		}

	}


}
