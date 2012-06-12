package com.hixi_hyi.idumo.android.core.exec;

import java.util.Collection;

import android.app.Activity;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.core.exec.CoreComponent;

public abstract class AndroidComponent extends CoreComponent {
	
	protected Activity	activity;
	
	// public Activity getActivity() {
	// return activity;
	// }
	
	public Collection<AndroidController> getAndroidControllers() {
		AndroidContainer container = (AndroidContainer) getContainer();
		return container.getAndroidControllers();
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
}
