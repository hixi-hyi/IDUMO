package jp.idumo.core.exec;

import java.util.Collection;

import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Connectable;
import jp.idumo.core.parts.Executable;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;


public abstract class CoreComponent {
	
	private CoreContainer container = null;
	private CoreSetting setting = new CoreSetting();
	private boolean isReady;
	
	/**
	 * @param item
	 * @see jp.idumo.core.exec.CoreContainer#add(jp.idumo.core.parts.Connectable)
	 */
	public void add(Connectable item) {
		container.add(item);
	}
	
	/**
	 * @param sender
	 * @param receiver
	 * @see jp.idumo.core.exec.CoreContainer#connect(jp.idumo.core.parts.Sendable,
	 *      jp.idumo.core.parts.Receivable)
	 */
	public void connect(Sendable sender, Receivable receiver) {
		container.connect(sender, receiver);
	}
	
	/**
	 * @return
	 * @see jp.idumo.core.exec.CoreContainer#getApplicationControllers()
	 */
	public Collection<CoreController> getApplicationControllers() {
		return container.getApplicationControllers();
	}
	
	/**
	 * @return container
	 */
	public CoreContainer getContainer() {
		return container;
	}
	
	/**
	 * @return
	 * @see jp.idumo.core.exec.CoreSetting#getLoopCount()
	 */
	public int getLoopCount() {
		return setting.getLoopCount();
	}
	
	/**
	 * @return
	 * @see jp.idumo.core.exec.CoreContainer#getRunnable()
	 */
	public Executable getRunnable() {
		return container.getRunnable();
	}
	
	/**
	 * @return
	 * @see jp.idumo.core.exec.CoreSetting#getSleepTime()
	 */
	public int getSleepTime() {
		return setting.getSleepTime();
	}
	
	/**
	 * @return isReady
	 */
	public boolean isReady() {
		return isReady;
	}
	
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
	 * @param loopCount
	 * @see jp.idumo.core.exec.CoreSetting#setLoopCount(int)
	 */
	public void setLoopCount(int loopCount) {
		setting.setLoopCount(loopCount);
	}
	
	/**
	 * @param isReady
	 *            セットする isReady
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	/**
	 * @param sleepTime
	 * @see jp.idumo.core.exec.CoreSetting#setSleepTime(int)
	 */
	public void setSleepTime(int sleepTime) {
		setting.setSleepTime(sleepTime);
	}
	
	/**
	 * @throws IDUMOException
	 * @see jp.idumo.core.exec.CoreContainer#setup()
	 */
	public void setup() throws IDUMOException {
		container.setup();
	}
	
}
