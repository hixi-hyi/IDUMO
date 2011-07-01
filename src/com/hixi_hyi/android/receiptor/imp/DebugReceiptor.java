package com.hixi_hyi.android.receiptor.imp;

import android.util.Log;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.receiptor.DataReceiptor;

public class DebugReceiptor extends DataReceiptor {
	@Override
	public void handlerChanged(HandlerEvent e) {
		Log.v("Receiptor", e.toString());
	}

	@Override
	public void setParameter(PipeData parameter) {

	}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		return false;
	}

	@Override
	public boolean isRegist(DataHandlerInterface handlerInterface) {
		return true;
	}
}
