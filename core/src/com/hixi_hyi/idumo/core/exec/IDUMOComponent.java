package com.hixi_hyi.idumo.core.exec;

import java.util.Collection;

import com.hixi_hyi.idumo.core.IDUMOController;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOParts;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public abstract class IDUMOComponent {

	private IDUMOContainer			container	= null;
	private IDUMOSetting	setting		= new IDUMOSetting();
	private boolean					isReady;


	abstract public void onIdumoMakeFlowChart() throws IDUMOException;
	abstract public void onIdumoPrepare();

	/**
	 * @param container
	 *            セットする container
	 */
	public void setContainer(IDUMOContainer container) {
		this.container = container;
	}

	/**
	 * @return container
	 */
	public IDUMOContainer getContainer() {
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
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOContainer#add(com.hixi_hyi.idumo.core.parts.IDUMOParts)
	 */
	public void add(IDUMOParts item) {
		container.add(item);
	}

	/**
	 * @param sender
	 * @param receiver
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOContainer#connect(com.hixi_hyi.idumo.core.parts.IDUMOSender,
	 *      com.hixi_hyi.idumo.core.parts.IDUMOReceiver)
	 */
	public void connect(IDUMOSender sender, IDUMOReceiver receiver) {
		container.connect(sender, receiver);
	}

	/**
	 * @throws IDUMOException
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOContainer#setup()
	 */
	public void setup() throws IDUMOException {
		container.setup();
	}

	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOContainer#getRunnable()
	 */
	public IDUMORunnable getRunnable() {
		return container.getRunnable();
	}

	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOSetting#getLoopCount()
	 */
	public int getLoopCount() {
		return setting.getLoopCount();
	}

	/**
	 * @param loopCount
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOSetting#setLoopCount(int)
	 */
	public void setLoopCount(int loopCount) {
		setting.setLoopCount(loopCount);
	}

	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOSetting#getSleepTime()
	 */
	public int getSleepTime() {
		return setting.getSleepTime();
	}

	/**
	 * @param sleepTime
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOSetting#setSleepTime(int)
	 */
	public void setSleepTime(int sleepTime) {
		setting.setSleepTime(sleepTime);
	}

	/**
	 * @return
	 * @see com.hixi_hyi.idumo.core.exec.IDUMOContainer#getApplicationControllers()
	 */
	public Collection<IDUMOController> getApplicationControllers() {
		return container.getApplicationControllers();
	}

}
