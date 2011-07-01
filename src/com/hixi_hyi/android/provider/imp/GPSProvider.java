package com.hixi_hyi.android.provider.imp;

import java.util.Collection;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

import com.hixi_hyi.android.provider.DataProvider;
import com.hixi_hyi.android.provider.DataProviderInterface;

public class GPSProvider extends DataProvider implements DataProviderInterface,
		LocationListener {

	private LocationManager locationManager;

	public GPSProvider(LocationManager manager){
		this.locationManager = manager;
	}

	@Override
	public void onLocationChanged(Location location) {
        Log.v("----------", "----------");
        Log.v("Latitude", String.valueOf(location.getLatitude()));
        Log.v("Longitude", String.valueOf(location.getLongitude()));
        Log.v("Accuracy", String.valueOf(location.getAccuracy()));
        Log.v("Altitude", String.valueOf(location.getAltitude()));
        Log.v("Time", String.valueOf(location.getTime()));
        Log.v("Speed", String.valueOf(location.getSpeed()));
        Log.v("Bearing", String.valueOf(location.getBearing()));
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
			Log.v("Status", "AVAILABLE");
			break;
		case LocationProvider.OUT_OF_SERVICE:
			Log.v("Status", "OUT_OF_SERVICE");
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			Log.v("Status", "TEMPORARILY_UNAVAILABLE");
			break;
		}
	}

	public String useSensorType() {
		return LocationManager.GPS_PROVIDER;
	}

	@Override
	public Collection<Class<?>> getNotifyDataType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void onResume() {
		locationManager.requestLocationUpdates(useSensorType(),0,0,this);
		super.onResume();
	}

	@Override
	public void onPause() {
		locationManager.removeUpdates(this);
		super.onPause();
	}


}
