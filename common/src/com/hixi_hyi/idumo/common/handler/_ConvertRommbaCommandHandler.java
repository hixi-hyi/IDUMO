package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.common.component._ConvertRoombaCommand;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.PrimitiveDataNumber;
import com.hixi_hyi.idumo.core.data.PrimitiveDataString;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorType;

public class _ConvertRommbaCommandHandler implements IDUMOSendable, IDUMOReceivable {
	
	private IDUMOSendable			sender;
	private IDUMOReceiveValidatorSize	vSize	= new IDUMOReceiveValidatorSize(1);
	private IDUMOReceiveValidatorType	vType	= new IDUMOReceiveValidatorType(1, PrimitiveDataString.class);
	
	public _ConvertRommbaCommandHandler() {}
	
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
	public FlowingData onCall() {
		PrimitiveDataString data = (PrimitiveDataString) sender.onCall().next();
		String command = data.getString();
		FlowingData p = new FlowingData();
		if (_ConvertRoombaCommand.containsKey(command)) {
			p.add(new PrimitiveDataNumber(_ConvertRoombaCommand.getCommand(command)));
		}
		return p;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new ConnectDataTypeSingle(PrimitiveDataString.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new ConnectDataTypeSingle(PrimitiveDataNumber.class);
	}
	
}
