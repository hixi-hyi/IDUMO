package com.hixi_hyi.idumo.android.execution.app;

import android.content.Context;
import android.location.LocationManager;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.GPSSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.ReversedGeocordingHandler;

public class ReverseGeoLocationComponent extends AbstractAndroidExecutionComponent {
	
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		LocationManager location = (LocationManager) (activity.getSystemService(Context.LOCATION_SERVICE));
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
