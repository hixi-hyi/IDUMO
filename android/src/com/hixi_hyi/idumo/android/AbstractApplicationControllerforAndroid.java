package com.hixi_hyi.idumo.android;

public class AbstractApplicationControllerforAndroid implements
		ApplicationControlforAndroid {

	ApplicationControlforAndroid aci;

	public AbstractApplicationControllerforAndroid(ApplicationControlforAndroid aci){
		this.aci = aci;
	}

	@Override
	public void onStart() {
		aci.onStart();
	}

	@Override
	public void onRestart() {
		aci.onRestart();
	}

	@Override
	public void onResume() {
		aci.onResume();
	}

	@Override
	public void onPause() {
		aci.onPause();
	}

	@Override
	public void onStop() {
		aci.onStop();
	}

	@Override
	public void onDestroy() {
		aci.onDestroy();
	}

}
