package com.hixi_hyi.android.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class UpdateHandler extends Handler {
	private Updatable updatable;
	private boolean sleep;
	{
		sleep = false;
	}

	public UpdateHandler(Updatable updatable) {
		super();
		this.updatable = updatable;
	}

	@Override
	public void handleMessage(Message msg) {
		Log.v("UpdateHandler", "update");
		sleep = false;
		updatable.update();
	}

	public boolean isSleep() {
		return sleep;
	}

	public void sleep(long sleepTime) {
		Log.v("UpdateHandler", "sleep" + sleepTime);
		sleep = true;
		removeMessages(0);
		sendMessageDelayed(obtainMessage(0), sleepTime);
	}
}