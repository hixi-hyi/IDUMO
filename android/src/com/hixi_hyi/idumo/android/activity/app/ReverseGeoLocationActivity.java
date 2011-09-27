package com.hixi_hyi.idumo.android.activity.app;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AndroidExecution;
import com.hixi_hyi.idumo.android.execution.app.ReverseGeoLocationComponent;

public class ReverseGeoLocationActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new ReverseGeoLocationComponent());
//		AbstractAndroidExecutionComponent component = new ReverseGeoLocationComponent();
//		AndroidExecution execution = new AndroidExecution(component);
//		setExecution(execution);
	}

}
