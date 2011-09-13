package com.hixi_hyi.idumo.android;

public class AbstractApplicationControllerforAndroid implements
		ApplicationControllerForAndroid {

	ApplicationControllerForAndroid aci;

	public AbstractApplicationControllerforAndroid(ApplicationControllerForAndroid aci){
		this.aci = aci;
	}

	@Override
	public void onIdumoStart() {
		aci.onIdumoStart();
	}

	@Override
	public void onIdumoRestart() {
		aci.onIdumoRestart();
	}

	@Override
	public void onIdumoResume() {
		aci.onIdumoResume();
	}

	@Override
	public void onIdumoPause() {
		aci.onIdumoPause();
	}

	@Override
	public void onIdumoStop() {
		aci.onIdumoStop();
	}

	@Override
	public void onIdumoDestroy() {
		aci.onIdumoDestroy();
	}

}
