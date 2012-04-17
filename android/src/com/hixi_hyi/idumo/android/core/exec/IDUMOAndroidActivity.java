package com.hixi_hyi.idumo.android.core.exec;

import android.app.Activity;
import android.os.Handler;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.exec.IDUMOActivity;
import com.hixi_hyi.idumo.core.exec.IDUMOController;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;

public class IDUMOAndroidActivity implements IDUMOActivity, Runnable {

	private IDUMOAndroidComponent	component;
	private Handler								handler	= new Handler();

	public IDUMOAndroidActivity(IDUMOAndroidComponent component) {
		this.component = component;
		this.component.setContainer(new IDUMOAndroidContainer());
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
	 * @see com.hixi_hyi.idumo.android.core.exec.IDUMOAndroidComponent#setActivity(android.app.Activity)
	 */
	public void setActivity(Activity activity) {
		component.setActivity(activity);
	}

}
