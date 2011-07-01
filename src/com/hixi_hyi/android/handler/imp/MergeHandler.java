package com.hixi_hyi.android.handler.imp;

import java.util.ArrayList;
import java.util.Collection;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.event.IdumoEvent;
import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.handler.DataHandler;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.provider.DataProviderInterface;

public class MergeHandler extends DataHandler {

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

	public void changed(IdumoEvent event){

	}

	@Override
	public void providerChanged(ProviderEvent event) {
	}

	@Override
	public boolean isRegist(DataProviderInterface providerInterface) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void handlerChanged(HandlerEvent event) {
	}

	@Override
	public boolean isRegist(DataHandlerInterface handlerInterface) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
