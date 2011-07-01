package com.hixi_hyi.android.provider;

import java.util.ArrayList;

import android.hardware.SensorManager;

import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.eventlistener.ProviderListener;

public abstract class DataProvider implements DataProviderInterface {
	protected ArrayList<ProviderListener> listeners;
	{
		listeners = new ArrayList<ProviderListener>();
	}

	@Override
	public void addProviderListener(ProviderListener l) {
		listeners.add(l);
	}

	public void dataChange(ProviderEvent event) {
		for (ProviderListener l : listeners) {
			l.providerChanged(event);
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
