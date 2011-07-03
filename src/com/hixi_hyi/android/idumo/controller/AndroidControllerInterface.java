package com.hixi_hyi.android.idumo.controller;

import android.app.Activity;
import android.os.Bundle;

public interface AndroidControllerInterface extends Runnable{
	void onCreate(Activity activity);

	void onStart();

	void onRestart();

	void onResume();

	void onPause();

	void onStop();

	void onDestroy();
}
