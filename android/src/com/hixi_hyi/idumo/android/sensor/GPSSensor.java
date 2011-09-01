package com.hixi_hyi.idumo.android.sensor;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.hixi_hyi.idumo.android.ApplicationControllerforAndroid;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * GPSセンサ
 * @author Hiroyoshi HOUCHI
 *
 */
public enum GPSSensor implements LocationListener , ApplicationControllerforAndroid{

	INSTANCE;

	private LocationManager locationManager;
	private Location location;
	private boolean isReady;

	public void init(LocationManager manager){
		this.locationManager = manager;
		location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
//		AndroidLogger.d(location);
	}


	public boolean isReady(){
		if(location!=null){
			return true;
		}
		return false;
//		return isReady;
	}
	/**
	 * @return
	 * @see android.location.Location#getAccuracy()
	 */
	public float getAccuracy() {
		return location.getAccuracy();
	}

	/**
	 * @return
	 * @see android.location.Location#getAltitude()
	 */
	public double getAltitude() {
		return location.getAltitude();
	}

	/**
	 * @return
	 * @see android.location.Location#getBearing()
	 */
	public float getBearing() {
		return location.getBearing();
	}

	/**
	 * @return
	 * @see android.location.Location#getLatitude()
	 */
	public double getLatitude() {
		return location.getLatitude();
	}

	/**
	 * @return
	 * @see android.location.Location#getLongitude()
	 */
	public double getLongitude() {
		return location.getLongitude();
	}

	/**
	 * @return
	 * @see android.location.Location#getSpeed()
	 */
	public float getSpeed() {
		return location.getSpeed();
	}

	/**
	 * @return
	 * @see android.location.Location#getTime()
	 */
	public long getTime() {
		return location.getTime();
	}


	@Override
	public void onLocationChanged(Location location) {
		this.isReady = true;
		this.location = location;
		LogManager.debug("Latitude:"+String.valueOf(location.getLatitude()));
		LogManager.debug("Longitude:"+String.valueOf(location.getLongitude()));
		LogManager.debug("Accurary:"+String.valueOf(location.getAccuracy()));
		LogManager.debug("Altitude:"+String.valueOf(location.getAltitude()));
		LogManager.debug("Time:"+String.valueOf(location.getTime()));
		LogManager.debug("Speed:"+String.valueOf(location.getSpeed()));
		LogManager.debug("Bearing:"+String.valueOf(location.getBearing()));

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		switch (status) {
		case LocationProvider.AVAILABLE:
			LogManager.debug("STATUS AVAILABLE");
			break;
		case LocationProvider.OUT_OF_SERVICE:
			LogManager.debug("STATUS OUT_OF_SERVICE");
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			LogManager.debug("STATUS TEMPORARILY_UNAVAILABLE");
			break;
		}
	}

	public String useSensorType() {
		return LocationManager.GPS_PROVIDER;
	}


//必須機能なのでここに実装してみた
	@Override
	public void onResume() {
		locationManager.requestLocationUpdates(useSensorType(),0,0,this);
	}

	@Override
	public void onPause() {
		locationManager.removeUpdates(this);
	}

	@Override
	public void onStart() {}

	@Override
	public void onRestart() {}

	@Override
	public void onStop() {}

	@Override
	public void onDestroy() {}

}
