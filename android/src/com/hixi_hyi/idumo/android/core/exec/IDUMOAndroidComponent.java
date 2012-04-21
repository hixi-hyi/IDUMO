package com.hixi_hyi.idumo.android.core.exec;

import java.util.Collection;

import android.app.Activity;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;

public abstract class IDUMOAndroidComponent extends IDUMOComponent {
	
	protected Activity	activity;
	
	// public Activity getActivity() {
	// return activity;
	// }
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public Collection<AndroidController> getAndroidControllers() {
		IDUMOAndroidContainer container = (IDUMOAndroidContainer) getContainer();
		return container.getAndroidControllers();
	}
	
}
