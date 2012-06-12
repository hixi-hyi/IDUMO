package com.hixi_hyi.idumo.common.handler.manifact;

import com.hixi_hyi.idumo.core.data.Data;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ArrayConnectDataType;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class ArrayGetTopHandler implements Sendable,Receivable{

	private Sendable sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}

	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(Data.class);
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
