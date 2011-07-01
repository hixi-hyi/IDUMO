package com.hixi_hyi.android.provider.imp;

import java.util.Collection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hixi_hyi.android.provider.DataProvider;

public class OrientationProvider extends DataProvider implements
		SensorEventListener {
    float[] inR = new float[16];
    float[] outR = new float[16];
    float[] I = new float[16];

    private float orientation[] = new float[3];
    private float magneticField[] = new float[3];
    private float accel[] = new float[3];
    private SensorManager sensorManager;

    public OrientationProvider(SensorManager manager){
    	sensorManager = manager;
    }
	@Override
	public void onResume() {
		Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_UI);
		Sensor magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		sensorManager.registerListener(this, magnetic,SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onPause() {
		sensorManager.unregisterListener(this);
	}

	@Override
	public Collection<Class<?>> getNotifyDataType() {
		return null;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            for (int i = 0; i < 3; i++)
                magneticField[i] = event.values[i];
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            for (int i = 0; i < 3; i++)
                accel[i] = event.values[i];
        }
        SensorManager.getRotationMatrix(inR, I, accel, magneticField);
        SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X,
                SensorManager.AXIS_Z, outR);
        SensorManager.getOrientation(outR, orientation);

    }
}
