package com.hixi_hyi.idumo.android.handler;

import java.util.List;

import com.hixi_hyi.idumo.android.util.LogUtil;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.ReceiverWithOption;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;

public class ThroughHandler implements Sender,ReceiverWithOption {

	private Sender provider;

	@Override
	public PipeData getData() {
		LogUtil.d();
		if(!provider.isReady()){
			return null;
		}
		return provider.getData();
	}

	@Override
	public List<Class<?>> getDataType() throws IdumoException {
		return provider.getDataType();
	}

	@Override
	public boolean setSender(Sender... provider) {
		if(provider.length==getInputSize()){
			this.provider = provider[0];
			return true;
		}
		return false;
	}

	@Override
	public int getInputSize() {
		return 1;
	}

	@Override
	public boolean isReady() {
		return (provider!=null)&&provider.isReady();
	}

}
