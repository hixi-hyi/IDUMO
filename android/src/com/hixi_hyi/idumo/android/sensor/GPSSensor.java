/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
		// isReady = true;
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
