package com.hixi_hyi.android.eventlistener;

import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.handler.DataHandlerInterface;

public interface HandlerListener extends IdumoListener {
	public void handlerChanged(HandlerEvent event);
	public boolean isRegist(DataHandlerInterface handlerInterface);

}
