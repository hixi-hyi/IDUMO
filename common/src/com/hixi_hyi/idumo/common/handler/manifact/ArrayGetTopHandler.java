package com.hixi_hyi.idumo.common.handler.manifact;

import com.hixi_hyi.idumo.core.data.Data;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeArray;
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
	public ConnectDataType receivableType() {
		return new ConnectDataTypeArray(Data.class);
	}

	@Override
	public FlowingData onCall() {
		Data d = sender.onCall().next();
		return new FlowingData(d);
	}

	@Override
	public ConnectDataType sendableType() {
		return sender.sendableType();
	}


}
