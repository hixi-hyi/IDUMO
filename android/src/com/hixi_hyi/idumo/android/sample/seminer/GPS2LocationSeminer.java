package com.hixi_hyi.idumo.android.sample.seminer;

import android.content.Context;
import android.location.LocationManager;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecution;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.GPSSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.handler.ReversedGeocordingHandler;
import com.hixi_hyi.idumo.core.util.LogManager;

public class GPS2LocationSeminer extends AbstractAndroidExecution {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		LocationManager location = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		GPSSensor gpsSensor = GPSSensor.INSTANCE;
		gpsSensor.init(location);
		add(gpsSensor);
		GPSProvider gps1 = new GPSProvider(gpsSensor);
		gps1.setOption(GPSProvider.Type.LATITUDE);
		add(gps1);
		GPSProvider gps2 = new GPSProvider(gpsSensor);
		gps2.setOption(GPSProvider.Type.LONGITUDE);
		add(gps2);
		ReversedGeocordingHandler rgh = new ReversedGeocordingHandler();
		add(rgh);
		TextViewReceiptor textview = new TextViewReceiptor(this);
		add(textview);
		connect(gps1, rgh);
		connect(gps2, rgh);
		connect(rgh, textview);
		setup();
	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}



}
