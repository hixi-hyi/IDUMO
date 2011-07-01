package com.hixi_hyi.android;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.handler.imp.DebugHandler;
import com.hixi_hyi.android.handler.imp.DelayHandler;
import com.hixi_hyi.android.handler.imp.GoogleBarChartHandler;
import com.hixi_hyi.android.handler.imp.ImpoundHandler;
import com.hixi_hyi.android.provider.DataProviderInterface;
import com.hixi_hyi.android.provider.imp.AccelerometerXProvider;
import com.hixi_hyi.android.receiptor.DataReceiptor;
import com.hixi_hyi.android.receiptor.DataReceiptorInterface;
import com.hixi_hyi.android.receiptor.imp.DebugReceiptor;
import com.hixi_hyi.android.receiptor.imp.ImageViewReceiptor;
import com.hixi_hyi.android.receiptor.imp.TextReceiptor;
import com.hixi_hyi.android.receiptor.imp.TextViewReceiptor;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;

/**
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class AccelToGoogleChart extends Activity {

	private SensorManager sensorManager;
	private AccelerometerXProvider accelProvider;
	private ImpoundHandler impoundHandler;
	private DelayHandler delayHandler;
	private GoogleBarChartHandler barChartHandler;
	private ImageViewReceiptor imageViewReceiptor;
	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		// Provider
		accelProvider = new AccelerometerXProvider(sensorManager);

		// Handler
		delayHandler = new DelayHandler(100);
		impoundHandler = new ImpoundHandler(5);
		barChartHandler = new GoogleBarChartHandler();
		// Receiptor
		imageViewReceiptor = new ImageViewReceiptor(this);

		accelProvider.addProviderListener(delayHandler);
		delayHandler.addHandlerListener(impoundHandler);
		impoundHandler.addHandlerListener(barChartHandler);
		barChartHandler.addHandlerListener(imageViewReceiptor);

	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		accelProvider.onPause();
		super.onPause();
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		accelProvider.onResume();
		super.onResume();
	}

}
