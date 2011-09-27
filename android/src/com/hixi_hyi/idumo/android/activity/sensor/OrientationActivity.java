package com.hixi_hyi.idumo.android.activity.sensor;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AndroidExecution;
import com.hixi_hyi.idumo.android.execution.app.ReverseGeoLocationComponent;
import com.hixi_hyi.idumo.android.execution.sensor.AccelerometerComponent;
import com.hixi_hyi.idumo.android.execution.sensor.GPS2Component;
import com.hixi_hyi.idumo.android.execution.sensor.LightComponent;
import com.hixi_hyi.idumo.android.execution.sensor.MagneticComponent;
import com.hixi_hyi.idumo.android.execution.sensor.OrientationComponent;

public class OrientationActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new OrientationComponent());
//		AbstractAndroidExecutionComponent component = new OrientationComponent();
//		AndroidExecution execution = new AndroidExecution(component);
//		setExecution(execution);
	}

}
