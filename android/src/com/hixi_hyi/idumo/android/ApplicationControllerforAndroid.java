package com.hixi_hyi.idumo.android;

import com.hixi_hyi.idumo.core.ApplicationController;

/**
 * ApplicationのON/OFF時に必要な処理を書くためのインタフェース
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public interface ApplicationControllerforAndroid extends ApplicationController{
	void onRestart();

	void onResume();

	void onPause();

	void onDestroy();
}
