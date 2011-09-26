package com.hixi_hyi.idumo.android.exec;

import java.util.Collection;

import android.app.Activity;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.front.AndroidContainer;
import com.hixi_hyi.idumo.core.exec.AbstractExecutionComponent;
import com.hixi_hyi.idumo.core.util.LogManager;

public abstract class AbstractAndroidExecutionComponent extends AbstractExecutionComponent {

	private Activity activity;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Collection<AndroidController> getAndroidControllers() {
		AndroidContainer container = (AndroidContainer) getContainer();
		return container.getAndroidControllers();
	}

}
