package com.hixi_hyi.idumo.console.exec;

import com.hixi_hyi.idumo.core.ApplicationController;
import com.hixi_hyi.idumo.core.IdumoComponent;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoExecution;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.front.IdumoContainer;
import com.hixi_hyi.idumo.core.front.IdumoExecutionSetting;
import com.sun.org.apache.bcel.internal.generic.NEW;

public abstract class AbstractConsoleExecution implements IdumoExecution {

	private IdumoContainer	container = new IdumoContainer();
	private IdumoExecutionSetting setting = new IdumoExecutionSetting();
	private boolean isReady;

	/**
	 * @return isReady
	 */
	public boolean isReady() {
		return isReady;
	}

	@Override
	public void onIdumoCreated() throws IdumoException{
		onIdumoMakeFlowChart();
		setup();
		onIdumoPrepare();
	}

	@Override
	public void onIdumoStart() {
		onIdumoPrepare();
		for (ApplicationController controller : container.getApplicationControllers()) {
			controller.onIdumoStart();
		}
		isReady=true;
	}

	@Override
	public void onIdumoStop() {
		for (ApplicationController controller : container.getApplicationControllers()) {
			controller.onIdumoStop();
		}
		isReady=false;
	}

	@Override
	public void onIdumoExec() throws IdumoRuntimeException {
		while(!isReady()){
			try {
				Thread.sleep(getSleepTime());
			} catch (InterruptedException e) {}
		}
		IdumoRunnable runnable = getRunnable();
		while (!runnable.isReady()) {
			try {
				Thread.sleep(getSleepTime());
			} catch (InterruptedException e) {}
		}
		int count = getLoopCount();
		if(count==-1){
			runnable.run();
			try {
				Thread.sleep(getSleepTime());
			} catch (InterruptedException e) {}
		}else{
			for(int i = 0; i < count; i++){
				runnable.run();
				try {
					Thread.sleep(getSleepTime());
				} catch (InterruptedException e) {}
			}
		}

	}


	/**
	 * @param item
	 * @see com.hixi_hyi.idumo.core.front.IdumoContainer#add(com.hixi_hyi.idumo.core.IdumoComponent)
	 */
	public void add(IdumoComponent item) {
		container.add(item);
	}

	/**
	 * @param sender
	 * @param receiver
	 * @see com.hixi_hyi.idumo.core.front.IdumoContainer#connect(com.hixi_hyi.idumo.core.Sender, com.hixi_hyi.idumo.core.Receiver)
	 */
	public void connect(Sender sender, Receiver receiver) {
		container.connect(sender, receiver);
	}

	/**
	 * @throws IdumoException
	 * @see com.hixi_hyi.idumo.core.front.IdumoContainer#setup()
	 */
	public void setup() throws IdumoException {
		container.setup();
	}

	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.front.IdumoContainer#getRunnable()
	 */
	public IdumoRunnable getRunnable() {
		return container.getRunnable();
	}

	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.front.IdumoExecutionSetting#getLoopCount()
	 */
	public int getLoopCount() {
		return setting.getLoopCount();
	}

	/**
	 * @param loopCount
	 * @see com.hixi_hyi.idumo.core.front.IdumoExecutionSetting#setLoopCount(int)
	 */
	public void setLoopCount(int loopCount) {
		setting.setLoopCount(loopCount);
	}

	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.front.IdumoExecutionSetting#getSleepTime()
	 */
	public int getSleepTime() {
		return setting.getSleepTime();
	}

	/**
	 * @param sleepTime
	 * @see com.hixi_hyi.idumo.core.front.IdumoExecutionSetting#setSleepTime(int)
	 */
	public void setSleepTime(int sleepTime) {
		setting.setSleepTime(sleepTime);
	}



}
