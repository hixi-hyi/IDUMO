package com.hixi_hyi.idumo.android.exec;

import android.app.Activity;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.front.AndroidContainer;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoExecution;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.exec.AbstractExecutionComponent;
import com.hixi_hyi.idumo.core.front.IdumoContainer;

public class AndroidExecution implements IdumoExecution, Runnable {


	private AbstractAndroidExecutionComponent	component;
	private Handler								handler	= new Handler();


	public AndroidExecution(AbstractAndroidExecutionComponent component){
		this.component = component;
		this.component.setContainer(new AndroidContainer());
	}

	@Override
	public void onIdumoCreated() throws IdumoException {
		component.onIdumoMakeFlowChart();
		component.setup();
		component.onIdumoPrepare();
	}

	@Override
	public void onIdumoStart() {
		for (AndroidController controller : component.getAndroidControllers()) {
			controller.onIdumoStart();
			controller.onIdumoResume();
		}
		component.setReady(true);
	}

	@Override
	public void onIdumoStop() {
		for (AndroidController controller : component.getAndroidControllers()) {
			controller.onIdumoPause();
			controller.onIdumoStop();
		}
		component.setReady(false);
	}

	@Override
	public void onIdumoExec() throws IdumoRuntimeException {
		while (!component.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		IdumoRunnable runnable = component.getRunnable();
		while (!runnable.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		int count = component.getLoopCount();
		if (count == -1) {
			handler.post(runnable);
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		} else {
			for (int i = 0; i < count; i++) {
				handler.post(runnable);
				try {
					Thread.sleep(component.getSleepTime());
				} catch (InterruptedException e) {}
			}
		}
	}

	@Override
	public void run() {
		onIdumoExec();
	}

	/**
	 * @param activity
	 * @see com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent#setActivity(android.app.Activity)
	 */
	public void setActivity(Activity activity) {
		component.setActivity(activity);
	}


}
