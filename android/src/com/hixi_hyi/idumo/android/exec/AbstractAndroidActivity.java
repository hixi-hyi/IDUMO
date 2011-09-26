package com.hixi_hyi.idumo.android.exec;

import android.app.Activity;
import android.os.Bundle;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.util.LogManager;

public abstract class AbstractAndroidActivity extends Activity {

	private AndroidExecution	execution;

	public void setExecution(AndroidExecution execution) {
		this.execution = execution;
	}

	abstract public void init();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		execution.setActivity((Activity)this);
		try {
			execution.onIdumoCreated();
		} catch (IdumoException e) {
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
