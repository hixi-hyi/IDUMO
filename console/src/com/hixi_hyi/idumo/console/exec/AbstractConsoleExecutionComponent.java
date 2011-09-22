package com.hixi_hyi.idumo.console.exec;

import java.util.Collection;

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

public abstract class AbstractConsoleExecutionComponent implements IdumoExecutionComponent {

	private IdumoContainer	container = new IdumoContainer();
	private IdumoExecutionSetting setting = new IdumoExecutionSetting();
	private boolean isReady;

	/**
	 * @param isReady セットする isReady
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * @return isReady
	 */
	public boolean isReady() {
		return isReady;
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

	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.front.IdumoContainer#getApplicationControllers()
	 */
	public Collection<ApplicationController> getApplicationControllers() {
		return container.getApplicationControllers();
	}




}
