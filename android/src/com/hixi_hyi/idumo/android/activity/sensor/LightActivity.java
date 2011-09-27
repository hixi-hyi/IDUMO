package com.hixi_hyi.idumo.android.activity.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AndroidExecution;
import com.hixi_hyi.idumo.android.execution.app.ReverseGeoLocationComponent;
import com.hixi_hyi.idumo.android.execution.sensor.AccelerometerComponent;
import com.hixi_hyi.idumo.android.execution.sensor.GPS2Component;
import com.hixi_hyi.idumo.android.execution.sensor.LightComponent;

public class LightActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new LightComponent());
//		AbstractAndroidExecutionComponent component = new LightComponent();
//		AndroidExecution execution = new AndroidExecution(component);
//		setExecution(execution);
	}

}
