/**
 *
 */
package com.hixi_hyi.android.idumo;

import java.util.ArrayList;

import com.hixi_hyi.android.idumo.controller.AccelerometerToViewController;

import android.app.Activity;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * @author Hiroyoshi HOUCHI
 *
 */
public class Idumo extends Activity {

	AccelerometerToViewController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		controller = new AccelerometerToViewController();
		controller.onCreate(this);
	}

	@Override
	protected void onPause() {
		controller.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		controller.onDestroy();
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		controller.onRestart();
		super.onRestart();
	}

	@Override
	protected void onStart() {
		super.onStart();
		controller.onStart();
	}

	@Override
	protected void onStop() {
		controller.onStop();
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		controller.onResume();
	}

}
