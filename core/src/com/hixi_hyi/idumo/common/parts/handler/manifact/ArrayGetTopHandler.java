package com.hixi_hyi.idumo.common.parts.handler.manifact;

import com.hixi_hyi.idumo.core.annotation.IDUMOHandler;
import com.hixi_hyi.idumo.core.data.DataElement;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.ThroughElement;
import com.hixi_hyi.idumo.core.data.connect.ArrayConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

@IDUMOHandler(author = "Hiroyoshi HOUCHI", name = "配列の一番上を取得", description = "配列の一番上の値を取得する", receive = DataElement.class, send = ThroughElement.class)
public class ArrayGetTopHandler implements Sendable, Receivable {

	private Sendable				sender;
	private ReceiveValidatorSize	vSize	= new ReceiveValidatorSize(1);

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public FlowingData onCall() {
		DataElement d = sender.onCall().next();
		return new FlowingData(d);
	}

	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(DataElement.class);
	}

	@Override
	public ConnectDataType sendableType() {
		return sender.sendableType();
	}

	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}

}
