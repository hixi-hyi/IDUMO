package com.hixi_hyi.android.handler.imp;

import java.util.Collection;

import android.util.Log;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.event.IdumoEvent;
import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.eventlistener.HandlerListener;
import com.hixi_hyi.android.handler.DataHandler;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.provider.DataProviderInterface;

public class DebugHandler extends DataHandler {

	public DebugHandler() {
		super();
	}

	@Override
	public void providerChanged(ProviderEvent e) {
		localDataChange(e);
	}

	@Override
	public void handlerChanged(HandlerEvent e) {
		localDataChange(e);
	}

	public void localDataChange(IdumoEvent e) {
		HandlerEvent event = new HandlerEvent(e.getPipeData());
		Log.v(this.getClass().getSimpleName(), e.toString());
		super.dataChange(event);
	}

	@Override
	public void setParameter(PipeData parameter) {}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		return false;
	}

	@Override
	public boolean isRegist(DataProviderInterface providerInterface) {
		return true;
	}

	@Override
	public boolean isRegist(DataHandlerInterface handlerInterface) {
		return true;
	}

	@Override
	public Collection<Class<?>> getNotifyDataType() {
		// TODO これどうしよう…スルーしたい部分.入ってきたものをそのまま渡すだけだし．
		return null;
	}


}
