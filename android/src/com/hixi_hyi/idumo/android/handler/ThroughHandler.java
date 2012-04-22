package com.hixi_hyi.idumo.android.handler;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class ThroughHandler implements IDUMOSendable, IDUMOReceivable {
	
	private IDUMOSendable			provider;
	private ReceiveValidator	vSize	= new ReceiveValidatorSize(1);
	
	@Override
	public IDUMODataFlowing onCall() {
		IDUMOLogManager.log();
		if (!provider.isReady()) {
			return null;
		}
		return provider.onCall();
	}
	
	@Override
	public boolean setSender(IDUMOSendable... provider) throws IDUMOException {
		vSize.validate(provider);
		this.provider = provider[0];
		return true;
	}
	
	@Override
	public boolean isReady() {
		return (provider != null) && provider.isReady();
	}
	
	@Override
	public IDUMODataConnect receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public IDUMODataConnect sendableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
