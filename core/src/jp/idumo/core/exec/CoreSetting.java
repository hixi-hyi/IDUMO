package jp.idumo.core.exec;

public class CoreSetting {
	
	private int loopCount;
	private int sleepTime;
	
	/**
	 * @return loopCount
	 */
	public int getLoopCount() {
		return loopCount;
	}
	
	/**
	 * @return sleepTime
	 */
	public int getSleepTime() {
		return sleepTime;
	}
	
	/**
	 * @param loopCount
	 *            セットする loopCount
	 */
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}
	
	/**
	 * @param sleepTime
	 *            セットする sleepTime
	 */
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
}
