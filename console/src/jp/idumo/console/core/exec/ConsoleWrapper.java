package jp.idumo.console.core.exec;

import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.exec.CoreWrapper;

public abstract class ConsoleWrapper implements CoreWrapper {
	
	private ConsoleActivity	execution;
	
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
	
	public void setExecution(ConsoleActivity execution) {
		this.execution = execution;
	}
	
	public void setExecutionWithComponent(ConsoleComponent component) {
		execution = new ConsoleActivity(component);
	}
}
