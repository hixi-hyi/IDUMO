package com.hixi_hyi.idumo.android.activity.app;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AndroidExecution;
import com.hixi_hyi.idumo.android.execution.app.ReverseGeoLocationComponent;
import com.hixi_hyi.idumo.android.execution.app.TodaysWeatherComponent;

public class TodaysWeatherActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new TodaysWeatherComponent());
//		AbstractAndroidExecutionComponent component = new TodaysWeatherComponent();
//		AndroidExecution execution = new AndroidExecution(component);
//		setExecution(execution);
	}

}
