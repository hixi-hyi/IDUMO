package com.hixi_hyi.idumo.android.core.exec;

import android.app.Activity;
import android.os.Bundle;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOWrapper;

public abstract class IDUMOAndroidWrapper extends Activity implements IDUMOWrapper {
	
	private IDUMOAndroidActivity	execution;
	
	public void setExecutionWithComponent(IDUMOAndroidComponent component) {
		this.execution = new IDUMOAndroidActivity(component);
	}
	
	public void setExecution(IDUMOAndroidActivity execution) {
		this.execution = execution;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		execution.setActivity(this);
		try {
			execution.onIdumoCreated();
		} catch (IDUMOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onRestart() {
		execution.onIdumoStop();
		execution.onIdumoStart();
		super.onRestart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		execution.onIdumoStart();
		Thread thread = new Thread(execution);
		thread.start();
	}
	
	@Override
	public void onPause() {
		execution.onIdumoStop();
		super.onPause();
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
