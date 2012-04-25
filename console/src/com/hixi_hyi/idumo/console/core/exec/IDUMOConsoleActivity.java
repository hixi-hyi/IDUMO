package com.hixi_hyi.idumo.console.core.exec;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.exec.IDUMOActivity;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
import com.hixi_hyi.idumo.core.exec.IDUMOContainer;
import com.hixi_hyi.idumo.core.exec.IDUMOController;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;

public class IDUMOConsoleActivity implements IDUMOActivity {
	private IDUMOComponent	component;
	
	public IDUMOConsoleActivity(IDUMOComponent component) {
		this.component = component;
		this.component.setContainer(new IDUMOContainer());
	}
	
	@Override
	public void onIdumoCreated() throws IDUMOException {
		component.onIdumoMakeFlowChart();
		component.setup();
		component.onIdumoPrepare();
	}
	
	@Override
	public void onIdumoStart() {
		component.onIdumoPrepare();
		for (IDUMOController controller : component.getApplicationControllers()) {
			controller.onIdumoStart();
		}
		component.setReady(true);
	}
	
	@Override
	public void onIdumoStop() {
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
					runnable.run();
				}
				try {
					Thread.sleep(component.getSleepTime());
				} catch (InterruptedException e) {}
			}
		} else {
			for (int i = 0; i < count;) {
				if (runnable.isReady()) {
					runnable.run();
					i++;
				}
				try {
					Thread.sleep(component.getSleepTime());
				} catch (InterruptedException e) {}
			}
		}
		
	}
}
