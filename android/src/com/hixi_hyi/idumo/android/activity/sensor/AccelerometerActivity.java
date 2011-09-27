package com.hixi_hyi.idumo.android.activity.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AndroidExecution;
import com.hixi_hyi.idumo.android.execution.app.ReverseGeoLocationComponent;
import com.hixi_hyi.idumo.android.execution.sensor.AccelerometerComponent;

public class AccelerometerActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new AccelerometerComponent());
//		AbstractAndroidExecutionComponent component = new AccelerometerComponent();
//		AndroidExecution execution = new AndroidExecution(component);
//		setExecution(execution);
	}

}
