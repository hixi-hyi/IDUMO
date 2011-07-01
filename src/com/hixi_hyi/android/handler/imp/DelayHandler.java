package com.hixi_hyi.android.handler.imp;

import java.util.ArrayList;
import java.util.Collection;

import android.util.Log;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.event.IdumoEvent;
import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.eventlistener.HandlerListener;
import com.hixi_hyi.android.handler.DataHandler;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.handler.Updatable;
import com.hixi_hyi.android.handler.UpdateHandler;
import com.hixi_hyi.android.provider.DataProviderInterface;

public class DelayHandler extends DataHandler implements Updatable {
	private int delayTime;
	private HandlerEvent event;
	private UpdateHandler updateHandler;
	{
		updateHandler = new UpdateHandler(this);
	}

	public DelayHandler(int mtime){
		delayTime = mtime;
	}

	@Override
	public void setParameter(PipeData parameter) {}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		return false;
	}

	@Override
	public void providerChanged(ProviderEvent event) {
		this.localDataChange(event);
	}

	@Override
	public void handlerChanged(HandlerEvent event) {
		this.localDataChange(event);
	}

	@Override
	public void update() {
		Log.v(this.getClass().getSimpleName(), "update");
		super.dataChange(event);
	}

	public void localDataChange(IdumoEvent e) {
		event = new HandlerEvent(e.getPipeData());
		Log.v(this.getClass().getSimpleName(), "dataChange");
		if (!updateHandler.isSleep()) {
			Log.v(this.getClass().getSimpleName(), "NotSleep");
			updateHandler.sleep(delayTime);
		}
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
