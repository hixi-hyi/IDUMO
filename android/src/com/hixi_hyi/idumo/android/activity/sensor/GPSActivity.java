package com.hixi_hyi.idumo.android.activity.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AndroidExecution;
import com.hixi_hyi.idumo.android.execution.app.ReverseGeoLocationComponent;
import com.hixi_hyi.idumo.android.execution.sensor.AccelerometerComponent;
import com.hixi_hyi.idumo.android.execution.sensor.GPS2Component;

public class GPSActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new GPS2Component());
//		AbstractAndroidExecutionComponent component = new GPS2Component();
//		AndroidExecution execution = new AndroidExecution(component);
//		setExecution(execution);
	}

}
