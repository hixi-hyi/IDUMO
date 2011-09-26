package com.hixi_hyi.idumo.android.ztmp;

import android.content.Context;
import android.location.LocationManager;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecution;
import com.hixi_hyi.idumo.android.provider.GPSProvider;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.android.sensor.GPSSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.ReversedGeocordingHandler;

/**
 * AndroidのGPS情報から現在地を推測します．
 * 
 * @author Hiroyoshi
 * 
 */
public class GPS2LocationExecution extends AbstractAndroidExecution {
	
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
		
		connect(gps1, textview);
		connect(gps2, textview);
		
		connect(rgh, textview);
		
	}
	
	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
	
}
