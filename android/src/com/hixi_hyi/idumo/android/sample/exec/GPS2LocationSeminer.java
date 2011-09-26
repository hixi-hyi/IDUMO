package com.hixi_hyi.idumo.android.sample.exec;

import android.content.Context;
import android.location.LocationManager;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.GPSSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.ReversedGeocordingHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class GPS2LocationSeminer extends AbstractAndroidExecutionComponent {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		LogManager.log();
		LocationManager location = (LocationManager) (getActivity().getSystemService(Context.LOCATION_SERVICE));
		GPSSensor gpsSensor = GPSSensor.INSTANCE;
		gpsSensor.init(location);
		add(gpsSensor);
		LogManager.log();

		GPSProvider gps1 = new GPSProvider(gpsSensor);
		gps1.setOption(GPSProvider.Type.LATITUDE);
		add(gps1);

		GPSProvider gps2 = new GPSProvider(gpsSensor);
		gps2.setOption(GPSProvider.Type.LONGITUDE);
		add(gps2);

		ReversedGeocordingHandler rgh = new ReversedGeocordingHandler();
		add(rgh);

		TextViewReceiptor textview = new TextViewReceiptor(getActivity());
		add(textview);
		LogManager.log();

		connect(gps1, rgh);
		connect(gps2, rgh);
		connect(rgh, textview);
		LogManager.log();
	}

	@Override
	public void onIdumoPrepare() {
		LogManager.log();
		setLoopCount(-1);
		setSleepTime(1000);
		LogManager.log();
	}

}
