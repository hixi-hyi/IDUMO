package com.hixi_hyi.idumo.common.handler.manifact;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectArray;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class ArrayGetTopHandler implements IDUMOSendable,IDUMOReceivable{
	
	private IDUMOSendable sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}

	@Override
	public IDUMODataTypeConnect receivableType() {
		return new IDUMODataTypeConnectArray(IDUMOData.class);
	}

	@Override
	public IDUMODataFlowing onCall() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return sender.sendableType();
	}
	

}
