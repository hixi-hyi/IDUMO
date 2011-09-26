package com.hixi_hyi.idumo.android.execution.exec;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.exec.AndroidExecution;

public class GPS2LocationActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		AbstractAndroidExecutionComponent component = new GPS2LocationSeminer();
		AndroidExecution execution = new AndroidExecution(component);
		setExecution(execution);
	}

}
