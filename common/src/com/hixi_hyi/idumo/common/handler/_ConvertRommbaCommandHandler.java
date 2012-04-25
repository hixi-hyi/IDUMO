package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.common.component._ConvertRoombaCommand;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveNumber;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveString;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class _ConvertRommbaCommandHandler implements IDUMOSendable,
		IDUMOReceivable {

	private IDUMOSendable sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1,
			IDUMODataPrimitiveString.class);

	public _ConvertRommbaCommandHandler() {
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
		IDUMODataPrimitiveString data = (IDUMODataPrimitiveString) sender
				.onCall().next();
		String command = data.getString();
		IDUMODataFlowing p = new IDUMODataFlowing();
		if (_ConvertRoombaCommand.containsKey(command)) {
			p.add(new IDUMODataPrimitiveNumber(_ConvertRoombaCommand
					.getCommand(command)));
		}
		return p;
	}

	@Override
	public IDUMODataTypeConnect receivableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveString.class);
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveNumber.class);
	}

}
