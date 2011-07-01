package com.hixi_hyi.android.provider.imp;

import java.util.ArrayList;
import java.util.Collection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.eventlistener.ProviderListener;
import com.hixi_hyi.android.provider.DataProvider;
import com.hixi_hyi.android.provider.DataProviderInterface;

/**
 * @author Hiroyoshi HOUCHI
 *
 *
 */
public class AccelerometerProvider extends DataProvider implements
		DataProviderInterface, SensorEventListener {

	protected int accelerometerAccurary;
	protected float accel[];
	protected Sensor accelerometer;
	protected SensorManager sensorManager;

	public AccelerometerProvider(SensorManager manager) {
		listeners = new ArrayList<ProviderListener>();
		accel = new float[3];
		this.sensorManager = manager;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		if (sensor.getType() == useSensorType()) {
			accelerometerAccurary = accuracy;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == useSensorType()) {
			for (int i = 0; i < 3; i++) {
				accel[i] = event.values[i];
			}
		}
		providerChanged();
		// Log.v("AccelerometerProvider",getData().toString());
	}

	@Override
	public void onResume() {
		accelerometer = sensorManager.getDefaultSensor(useSensorType());
		sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_UI);
	}

	public int useSensorType() {
		return Sensor.TYPE_ACCELEROMETER;
	}

	@Override
	public void onPause() {
		sensorManager.unregisterListener(this);
	}

	public void providerChanged() {
		PipeData p = new PipeData();
		for (float f : accel) {
			p.add(f);
		}
		super.dataChange(new ProviderEvent(p));
	}

	@Override
	public Collection<Class<?>> getNotifyDataType() {
		ArrayList<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Float.class);
		type.add(Float.class);
		type.add(Float.class);
		return type;
	}
}
