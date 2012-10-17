package com.hixi_hyi.idumo.console.core.exec;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.exec.CoreActivity;
import com.hixi_hyi.idumo.core.exec.CoreController;
import com.hixi_hyi.idumo.core.parts.Executable;

public class ConsoleActivity implements CoreActivity {
	private ConsoleComponent component;
	
	public ConsoleActivity(ConsoleComponent component) {
		this.component = component;
		this.component.setContainer(new ConsoleContainer());
	}
	
	@Override
	public void onIdumoCreated() throws IDUMOException {
		component.onIdumoMakeFlowChart();
		component.setup();
		component.onIdumoPrepare();
	}
	
	@Override
	public void onIdumoExec() throws IDUMORuntimeException {
		while (!component.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			}
			catch (InterruptedException e) {}
		}
		Executable runnable = component.getRunnable();
		while (!runnable.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			}
			catch (InterruptedException e) {}
		}
		int count = component.getLoopCount();
		if (count == -1) {
			while (true) {
				if (runnable.isReady()) {
					runnable.run();
				}
				try {
					Thread.sleep(component.getSleepTime());
				}
				catch (InterruptedException e) {}
			}
		}
		for (int i = 0; i < count;) {
			if (runnable.isReady()) {
				runnable.run();
				i++;
			}
			try {
				Thread.sleep(component.getSleepTime());
			}
			catch (InterruptedException e) {}
		}
		
	}
	
	@Override
	public void onIdumoStart() {
		component.onIdumoPrepare();
		for (CoreController controller : component.getApplicationControllers()) {
			controller.onIdumoStart();
		}
		component.setReady(true);
	}
	
	@Override
	public void onIdumoStop() {
		for (CoreController controller : component.getApplicationControllers()) {
			controller.onIdumoStop();
		}
		component.setReady(false);
	}
}
