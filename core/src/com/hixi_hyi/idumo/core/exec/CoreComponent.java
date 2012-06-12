package com.hixi_hyi.idumo.core.exec;

import java.util.Collection;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Connectable;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Executable;
import com.hixi_hyi.idumo.core.parts.Sendable;

public abstract class CoreComponent {
	
	private CoreContainer	container	= null;
	private CoreSetting	setting		= new CoreSetting();
	private boolean			isReady;
	
	abstract public void onIdumoMakeFlowChart() throws IDUMOException;
	
	abstract public void onIdumoPrepare();
	
	/**
	 * @param container
	 *            セットする container
	 */
	public void setContainer(CoreContainer container) {
		this.container = container;
	}
	
	/**
	 * @return container
	 */
	public CoreContainer getContainer() {
		return container;
	}
	
	/**
	 * @param isReady
	 *            セットする isReady
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
	 * @see com.hixi_hyi.idumo.core.exec.CoreContainer#add(com.hixi_hyi.idumo.core.parts.Connectable)
	 */
	public void add(Connectable item) {
		container.add(item);
	}
	
	/**
	 * @param sender
	 * @param receiver
	 * @see com.hixi_hyi.idumo.core.exec.CoreContainer#connect(com.hixi_hyi.idumo.core.parts.Sendable,
	 *      com.hixi_hyi.idumo.core.parts.Receivable)
	 */
	public void connect(Sendable sender, Receivable receiver) {
		container.connect(sender, receiver);
	}
	
	/**
	 * @throws IDUMOException
	 * @see com.hixi_hyi.idumo.core.exec.CoreContainer#setup()
	 */
	public void setup() throws IDUMOException {
		container.setup();
	}
	
	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.exec.CoreContainer#getRunnable()
	 */
	public Executable getRunnable() {
		return container.getRunnable();
	}
	
	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.exec.CoreSetting#getLoopCount()
	 */
	public int getLoopCount() {
		return setting.getLoopCount();
	}
	
	/**
	 * @param loopCount
	 * @see com.hixi_hyi.idumo.core.exec.CoreSetting#setLoopCount(int)
	 */
	public void setLoopCount(int loopCount) {
		setting.setLoopCount(loopCount);
	}
	
	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.exec.CoreSetting#getSleepTime()
	 */
	public int getSleepTime() {
		return setting.getSleepTime();
	}
	
	/**
	 * @param sleepTime
	 * @see com.hixi_hyi.idumo.core.exec.CoreSetting#setSleepTime(int)
	 */
	public void setSleepTime(int sleepTime) {
		setting.setSleepTime(sleepTime);
	}
	
	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.exec.CoreContainer#getApplicationControllers()
	 */
	public Collection<CoreController> getApplicationControllers() {
		return container.getApplicationControllers();
	}
	
}
