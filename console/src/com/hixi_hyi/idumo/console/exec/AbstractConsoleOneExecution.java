package com.hixi_hyi.idumo.console.exec;

import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;

public abstract class AbstractConsoleOneExecution extends AbstractConsoleExecution {
	
	@Override
	public void onIdumoExec() throws IdumoRuntimeException {
		for (IdumoRunnable runnable : container.getRunnables()) {
			while (!runnable.isReady()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
			runnable.run();
		}
		
	}
	
}
