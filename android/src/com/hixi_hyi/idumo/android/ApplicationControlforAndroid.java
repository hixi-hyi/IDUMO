package com.hixi_hyi.idumo.android;

/**
 * ApplicationのON/OFF時に必要な処理を書くためのインタフェース
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public interface ApplicationControlforAndroid {
	void onStart();

	void onRestart();

	void onResume();

	void onPause();

	void onStop();

	void onDestroy();
}
