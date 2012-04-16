package com.hixi_hyi.idumo.android;

import com.hixi_hyi.idumo.core.IDUMOController;

/**
 * ApplicationのON/OFF時に必要な処理を書くためのインタフェース
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface AndroidController extends IDUMOController {
	void onIdumoRestart();
	
	void onIdumoResume();
	
	void onIdumoPause();
	
	void onIdumoDestroy();
}
