package com.hixi_hyi.idumo.android.handler;

import java.util.List;

import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class ThroughHandler implements IDUMOSender, IDUMOReceiver {
	
	private IDUMOSender	provider;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	
	@Override
	public PipeData getData() {
		LogManager.log();
		if (!provider.isReady()) {
			return null;
		}
		return provider.getData();
	}
	
	@Override
	public List<Class<?>> getDataType() throws IDUMOException {
		return provider.getDataType();
	}
	
	@Override
	public boolean setSender(IDUMOSender... provider) throws IDUMOException {
		vSize.validate(provider);
		this.provider = provider[0];
		return true;
	}
	
	
	@Override
	public boolean isReady() {
		return (provider != null) && provider.isReady();
	}
	
}
