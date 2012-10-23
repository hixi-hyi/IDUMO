package jp.idumo.android.core;

import jp.idumo.core.exec.CoreController;

/**
 * ApplicationのON/OFF時に必要な処理を書くためのインタフェース
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface AndroidController extends CoreController {
	void onIdumoDestroy();
	
	void onIdumoPause();
	
	void onIdumoRestart();
	
	void onIdumoResume();
}
