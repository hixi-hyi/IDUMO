package com.hixi_hyi.idumo.android.exec;

import android.app.Activity;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.front.AndroidContainer;
import com.hixi_hyi.idumo.core.IDUMOController;
import com.hixi_hyi.idumo.core.IDUMOActivity;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;

public class AndroidExecution implements IDUMOActivity, Runnable {

	private AbstractAndroidExecutionComponent	component;
	private Handler								handler	= new Handler();

	public AndroidExecution(AbstractAndroidExecutionComponent component) {
		this.component = component;
		this.component.setContainer(new AndroidContainer());
	}

	@Override
	public void onIdumoCreated() throws IDUMOException {
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
		for (IDUMOController controller : component.getApplicationControllers()) {
			controller.onIdumoStart();
		}
		component.setReady(true);
	}

	@Override
	public void onIdumoStop() {
		for (AndroidController controller : component.getAndroidControllers()) {
			controller.onIdumoPause();
			controller.onIdumoStop();
		}
		for (IDUMOController controller : component.getApplicationControllers()) {
			controller.onIdumoStop();
		}
		component.setReady(false);
	}

	@Override
	public void onIdumoExec() throws IDUMORuntimeException {
		while (!component.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		IDUMORunnable runnable = component.getRunnable();
		while (!runnable.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		int count = component.getLoopCount();
		if (count == -1) {
			while (true) {
				if (runnable.isReady()) {
					handler.post(runnable);
				}
				try {
					Thread.sleep(component.getSleepTime());
				} catch (InterruptedException e) {}
			}
		} else {
			for (int i = 0; i < count; ) {
				if (runnable.isReady()) {
					handler.post(runnable);
					i++;
				}
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
