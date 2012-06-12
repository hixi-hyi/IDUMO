package com.hixi_hyi.idumo.console.core.exec;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.CoreComponent;
import com.hixi_hyi.idumo.core.exec.CoreWrapper;

public abstract class IDUMOConsoleWrapper implements CoreWrapper {
	
	private IDUMOConsoleActivity	execution;
	
	public void setExecutionWithComponent(CoreComponent component) {
		this.execution = new IDUMOConsoleActivity(component);
	}
	
	public void setExecution(IDUMOConsoleActivity execution) {
		this.execution = execution;
	}
	
	public void exec() {
		init();
		try {
			execution.onIdumoCreated();
		} catch (IDUMOException e) {
			e.printStackTrace();
		}
		execution.onIdumoStart();
		execution.onIdumoExec();
		execution.onIdumoStop();
	}
}
