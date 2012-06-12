package com.hixi_hyi.idumo.console.core.exec;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.CoreWrapper;

public abstract class ConsoleWrapper implements CoreWrapper {
	
	private ConsoleActivity execution;
	
	public void exec() {
		init();
		try {
			execution.onIdumoCreated();
		}
		catch (IDUMOException e) {
			e.printStackTrace();
		}
		execution.onIdumoStart();
		execution.onIdumoExec();
		execution.onIdumoStop();
	}
	
	public void setExecution(ConsoleActivity execution) {
		this.execution = execution;
	}
	
	public void setExecutionWithComponent(ConsoleComponent component) {
		execution = new ConsoleActivity(component);
	}
}
