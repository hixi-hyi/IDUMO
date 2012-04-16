package com.hixi_hyi.idumo.android.sensor;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

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
//		isReady = true;
		// IDUMOAndroidLogger.d(location);
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
		IDUMOLogManager.debug("Latitude:" + String.valueOf(location.getLatitude()));
		IDUMOLogManager.debug("Longitude:" + String.valueOf(location.getLongitude()));
		IDUMOLogManager.debug("Accurary:" + String.valueOf(location.getAccuracy()));
		IDUMOLogManager.debug("Altitude:" + String.valueOf(location.getAltitude()));
		IDUMOLogManager.debug("Time:" + String.valueOf(location.getTime()));
		IDUMOLogManager.debug("Speed:" + String.valueOf(location.getSpeed()));
		IDUMOLogManager.debug("Bearing:" + String.valueOf(location.getBearing()));

	}

	@Override
	public void onProviderDisabled(String provider) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		switch (status) {
			case LocationProvider.AVAILABLE:
				IDUMOLogManager.debug("STATUS AVAILABLE");
				break;
			case LocationProvider.OUT_OF_SERVICE:
				IDUMOLogManager.debug("STATUS OUT_OF_SERVICE");
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				IDUMOLogManager.debug("STATUS TEMPORARILY_UNAVAILABLE");
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
