package com.hixi_hyi.idumo.android.sensor;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * GPSセンサ
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public enum GPSSensor implements LocationListener {
	
	INSTANCE;
	
	private LocationManager	locationManager;
	private Location		location;
	private boolean			isReady;
	private boolean			isRegister;
	private boolean			isInit;
	
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
	
	public void init(LocationManager manager) {
		isInit = true;
		this.locationManager = manager;
		location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
		isReady = true;
		// AndroidLogger.d(location);
	}
	
	public boolean isInit() {
		return isInit;
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		this.location = location;
		isReady = true;
		LogManager.debug("Latitude:" + String.valueOf(location.getLatitude()));
		LogManager.debug("Longitude:" + String.valueOf(location.getLongitude()));
		LogManager.debug("Accurary:" + String.valueOf(location.getAccuracy()));
		LogManager.debug("Altitude:" + String.valueOf(location.getAltitude()));
		LogManager.debug("Time:" + String.valueOf(location.getTime()));
		LogManager.debug("Speed:" + String.valueOf(location.getSpeed()));
		LogManager.debug("Bearing:" + String.valueOf(location.getBearing()));
		
	}
	
	@Override
	public void onProviderDisabled(String provider) {}
	
	@Override
	public void onProviderEnabled(String provider) {}
	
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
	
	public void register() {
		if (!isRegister) {
			isRegister = true;
			locationManager.requestLocationUpdates(useSensorType(), 0, 0, this);
		}
	}
	
	public void unregister() {
		if (isRegister) {
			isRegister = false;
			locationManager.removeUpdates(this);
		}
	}
	
	public String useSensorType() {
		return LocationManager.GPS_PROVIDER;
	}
	
}
