
package com.hixi_hyi.android.handler.imp.transform;

import java.util.ArrayList;
import java.util.Collection;

import android.util.Log;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.event.IdumoEvent;
import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.handler.DataHandler;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.provider.DataProviderInterface;

public class ToStringHandler extends DataHandler {

	@Override
	public void setParameter(PipeData parameter) {
	}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		return false;
	}

	@Override
	public Collection<Class<?>> getNotifyDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		types.add(String.class);
		return types;
	}

	public void localChanged(IdumoEvent event){
		StringBuilder sb = new StringBuilder();
		for(Object o:event.getPipeData()){
			sb.append(o.toString());
		}
		PipeData p = new PipeData();
		p.add(sb.toString());
		super.dataChange(new HandlerEvent(p));
	}

	@Override
	public void providerChanged(ProviderEvent event) {
		localChanged(event);
	}

	@Override
	public boolean isRegist(DataProviderInterface providerInterface) {
		return true;
//		if(providerInterface.getNotifyDataType().size()==1){
//			return true;
//		}
//		return false;
	}

	@Override
	public void handlerChanged(HandlerEvent event) {
		localChanged(event);
	}

	@Override
	public boolean isRegist(DataHandlerInterface handlerInterface) {
		return true;
//		if(handlerInterface.getNotifyDataType().size()==1){
//			return true;
//		}
//		return false;
	}

}
