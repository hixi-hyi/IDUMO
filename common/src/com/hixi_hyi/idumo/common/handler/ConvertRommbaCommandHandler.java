package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.common.component.ConvertRoombaCommand;
import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class ConvertRommbaCommandHandler implements IDUMOSendable, IDUMOReceivable {

	private IDUMOSendable sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1,
			IDUMOStringData.class);

	public ConvertRommbaCommandHandler() {
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMOStringData data = (IDUMOStringData) sender.onCall().next();
		String command = data.getString();
		IDUMODataFlowing p = new IDUMODataFlowing();
		if (ConvertRoombaCommand.containsKey(command)) {
			p.add(new IDUMONumberData(ConvertRoombaCommand.getCommand(command)));
		}
		return p;
	}

	@Override
	public IDUMODataConnect receivableType() {
		return new IDUMODataConnectSingle(IDUMOStringData.class);
	}

	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMONumberData.class);
	}

}
