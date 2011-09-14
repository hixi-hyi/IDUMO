package com.hixi_hyi.idumo.console.exec;

import com.hixi_hyi.idumo.core.ApplicationController;
import com.hixi_hyi.idumo.core.IdumoExecution;
import com.hixi_hyi.idumo.core.front.IdumoContainer;

public abstract class AbstractConsoleExecution implements IdumoExecution {
	
	protected IdumoContainer	container;
	
	@Override
	public void onIdumoStart() {
		for (ApplicationController controller : container.getController()) {
			controller.onIdumoStart();
		}
	}
	
	@Override
	public void onIdumoStop() {
		for (ApplicationController controller : container.getController()) {
			controller.onIdumoStop();
		}
	}
}
