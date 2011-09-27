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
import com.hixi_hyi.idumo.android.execution.sensor.ProximityComponent;

public class ProximityActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new ProximityComponent());
//		AbstractAndroidExecutionComponent component = new ProximityComponent();
//		AndroidExecution execution = new AndroidExecution(component);
//		setExecution(execution);
	}

}
