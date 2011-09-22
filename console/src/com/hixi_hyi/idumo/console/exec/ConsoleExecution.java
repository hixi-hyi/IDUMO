package com.hixi_hyi.idumo.console.exec;

import java.awt.Component;

import com.hixi_hyi.idumo.core.ApplicationController;
import com.hixi_hyi.idumo.core.IdumoComponent;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoExecution;
import com.hixi_hyi.idumo.core.IdumoExecutionComponent;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.front.IdumoContainer;
import com.hixi_hyi.idumo.core.front.IdumoExecutionSetting;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ConsoleExecution implements IdumoExecution {
	private AbstractConsoleExecutionComponent component;

	public ConsoleExecution(AbstractConsoleExecutionComponent component){
		this.component = component;
	}


	@Override
	public void onIdumoCreated() throws IdumoException{
		component.onIdumoMakeFlowChart();
		component.setup();
		component.onIdumoPrepare();
	}

	@Override
	public void onIdumoStart() {
		component.onIdumoPrepare();
		for (ApplicationController controller : component.getApplicationControllers()) {
			controller.onIdumoStart();
		}
		component.setReady(true);
	}

	@Override
	public void onIdumoStop() {
		for (ApplicationController controller : component.getApplicationControllers()) {
			controller.onIdumoStop();
		}
		component.setReady(false);
	}

	@Override
	public void onIdumoExec() throws IdumoRuntimeException {
		while(!component.isReady()){
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		IdumoRunnable runnable = component.getRunnable();
		while (!runnable.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		int count = component.getLoopCount();
		if(count==-1){
			runnable.run();
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}else{
			for(int i = 0; i < count; i++){
				runnable.run();
				try {
					Thread.sleep(component.getSleepTime());
				} catch (InterruptedException e) {}
			}
		}

	}



}
