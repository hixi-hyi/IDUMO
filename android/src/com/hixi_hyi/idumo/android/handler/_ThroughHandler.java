package com.hixi_hyi.idumo.android.handler;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class _ThroughHandler implements Sendable, Receivable {
	
	private Sendable		provider;
	private ReceiveValidator	vSize	= new ReceiveValidatorSize(1);
	
	@Override
	public FlowingData onCall() {
		LogManager.log();
		if (!provider.isReady()) {
			return null;
		}
		return provider.onCall();
	}
	
	@Override
	public void setSender(Sendable... provider) throws IDUMOException {
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
