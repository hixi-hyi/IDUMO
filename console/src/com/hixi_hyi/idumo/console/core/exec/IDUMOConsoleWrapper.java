package com.hixi_hyi.idumo.console.core.exec;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;

public abstract class IDUMOConsoleWrapper {

	private IDUMOConsoleActivity execution;

	public void setExecutionWithComponent(IDUMOComponent component){
		this.execution = new IDUMOConsoleActivity(component);
	}

	public void setExecution(IDUMOConsoleActivity execution) {
		this.execution = execution;
	}

	abstract public void init();

	public void exec(){
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
