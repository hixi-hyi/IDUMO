package com.hixi_hyi.idumo.android.handler;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidator;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;

public class _ThroughHandler implements IDUMOSendable, IDUMOReceivable {
	
	private IDUMOSendable		provider;
	private IDUMOReceiveValidator	vSize	= new IDUMOReceiveValidatorSize(1);
	
	@Override
	public FlowingData onCall() {
		IDUMOLogManager.log();
		if (!provider.isReady()) {
			return null;
		}
		return provider.onCall();
	}
	
	@Override
	public void setSender(IDUMOSendable... provider) throws IDUMOException {
		vSize.validate(provider);
		this.provider = provider[0];
	}
	
	@Override
	public boolean isReady() {
		return provider.isReady();
	}
	
	@Override
	public ConnectDataType receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public ConnectDataType sendableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
