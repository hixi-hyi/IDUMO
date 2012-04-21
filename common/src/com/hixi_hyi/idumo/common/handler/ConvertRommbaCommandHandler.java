package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.common.component.ConvertRoombaCommand;
import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class ConvertRommbaCommandHandler implements IDUMOSender, IDUMOReceiver {

	private IDUMOSender sender;
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
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
		return true;
	}

	@Override
	public IDUMOFlowingData onCall() {
		IDUMOStringData data = (IDUMOStringData) sender.onCall().next();
		String command = data.getString();
		IDUMOFlowingData p = new IDUMOFlowingData();
		if (ConvertRoombaCommand.containsKey(command)) {
			p.add(new IDUMONumberData(ConvertRoombaCommand.getCommand(command)));
		}
		return p;
	}

	@Override
	public Class<? extends IDUMOData> receivableType() {
		return IDUMOStringData.class;
	}

	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMONumberData.class;
	}

}
