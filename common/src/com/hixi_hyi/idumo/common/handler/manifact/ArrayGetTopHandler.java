package com.hixi_hyi.idumo.common.handler.manifact;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataBase;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectArray;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;

public class ArrayGetTopHandler implements IDUMOSendable,IDUMOReceivable{

	private IDUMOSendable sender;
	private IDUMOReceiveValidatorSize vSize = new IDUMOReceiveValidatorSize(1);

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
		IDUMOData d = sender.onCall().next();
		return new IDUMODataFlowing(d);
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return sender.sendableType();
	}


}
