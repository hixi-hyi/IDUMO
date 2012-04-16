package com.hixi_hyi.idumo.android.exec;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.front.AndroidContainer;
import com.hixi_hyi.idumo.core.ApplicationController;
import com.hixi_hyi.idumo.core.IdumoParts;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoExecution;
import com.hixi_hyi.idumo.core.IdumoExecutionComponent;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.front.IdumoExecutionSetting;

@Deprecated
public abstract class AbstractAndroidExecution extends Activity implements IdumoExecution, IdumoRunnable, IdumoExecutionComponent {

	private AndroidContainer		container	= new AndroidContainer();
	private IdumoExecutionSetting	setting		= new IdumoExecutionSetting();
	protected Thread				thread;
	protected boolean				isReady;
	protected Handler				handler		= new Handler();

	@Override
	public boolean isReady() {
		return isReady;
	}

	@Override
	public void run() {
		onIdumoExec();
	}

	@Override
	public void onIdumoCreated() throws IdumoException {
		onIdumoMakeFlowChart();
		setup();
		onIdumoPrepare();
	}

	@Override
	public void onIdumoStart() {
		for (ApplicationController controller : container.getApplicationControllers()) {
			controller.onIdumoStart();
		}
	}

	@Override
	public void onIdumoStop() {
		for (ApplicationController controller : container.getApplicationControllers()) {
			controller.onIdumoStop();
		}
	}

	@Override
	public void onIdumoExec() throws IdumoRuntimeException {
		while (!isReady()) {
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
		if (count == -1) {
			handler.post(runnable);
			try {
				Thread.sleep(getSleepTime());
			} catch (InterruptedException e) {}
		} else {
			for (int i = 0; i < count; i++) {
				handler.post(runnable);
				try {
					Thread.sleep(getSleepTime());
				} catch (InterruptedException e) {}
			}
		}

	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			onIdumoCreated();
		} catch (IdumoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		for (AndroidController controller : container.getAndroidControllers()) {
			controller.onIdumoStart();
		}
	}

	@Override
	public void onRestart() {
		for (AndroidController controller : container.getAndroidControllers()) {
			controller.onIdumoRestart();
		}
		super.onRestart();
	}

	@Override
	public void onResume() {
		super.onResume();
		for (AndroidController controller : container.getAndroidControllers()) {
			controller.onIdumoResume();
		}
		isReady = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onPause() {
		for (AndroidController controller : container.getAndroidControllers()) {
			controller.onIdumoPause();
		}
		isReady = false;
		super.onPause();
	}

	@Override
	public void onStop() {
		for (AndroidController controller : container.getAndroidControllers()) {
			controller.onIdumoStop();
		}
		super.onStop();
	}

	@Override
	public void onDestroy() {
		for (AndroidController controller : container.getAndroidControllers()) {
			controller.onIdumoDestroy();
		}
		super.onDestroy();
	}

	/**
	 * @param item
	 * @see com.hixi_hyi.idumo.core.front.IdumoContainer#add(com.hixi_hyi.idumo.core.IdumoParts)
	 */
	public void add(IdumoParts item) {
		container.add(item);
	}

	/**
	 * @param sender
	 * @param receiver
	 * @see com.hixi_hyi.idumo.core.front.IdumoContainer#connect(com.hixi_hyi.idumo.core.Sender,
	 *      com.hixi_hyi.idumo.core.Receiver)
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
