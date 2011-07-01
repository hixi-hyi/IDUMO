package com.hixi_hyi.android.handler;

import java.util.ArrayList;

import android.util.Log;

import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.eventlistener.HandlerListener;

public abstract class DataHandler implements DataHandlerInterface {
	protected ArrayList<HandlerListener> listeners;

	{
		this.listeners = new ArrayList<HandlerListener>();
	}

	@Override
	public void addHandlerListener(HandlerListener l) {
		Log.v(this.getClass().getSimpleName(), "add:"
				+ l.getClass().getSimpleName());
		listeners.add(l);
		for (HandlerListener hl : listeners) {
			Log.v(this.getClass().getSimpleName(), hl.getClass()
					.getSimpleName());
		}
	}

	public void dataChange(HandlerEvent event) {
		for (HandlerListener l : listeners) {
			Log.v(this.getClass().getSimpleName(), "event:"
					+ l.getClass().getSimpleName());
			l.handlerChanged(event);
		}
	}

	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.IdumoInterface#onStart()
	 */
	@Override
	public void onStart() {
	}

	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.IdumoInterface#onRestart()
	 */
	@Override
	public void onRestart() {
	}

	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.IdumoInterface#onResume()
	 */
	@Override
	public void onResume() {
	}

	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.IdumoInterface#onPause()
	 */
	@Override
	public void onPause() {
	}

	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.IdumoInterface#onStop()
	 */
	@Override
	public void onStop() {
	}

	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.IdumoInterface#onDestroy()
	 */
	@Override
	public void onDestroy() {
	}
}
