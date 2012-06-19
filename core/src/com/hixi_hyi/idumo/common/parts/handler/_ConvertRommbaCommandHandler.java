package com.hixi_hyi.idumo.common.parts.handler;

import com.hixi_hyi.idumo.common.component._ConvertRoombaCommand;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.NumberPrimitiveElement;
import com.hixi_hyi.idumo.core.data.primitive.StringPrimitiveElement;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class _ConvertRommbaCommandHandler implements Sendable, Receivable {

	private Sendable sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1, StringPrimitiveElement.class);

	public _ConvertRommbaCommandHandler() {}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public FlowingData onCall() {
		StringPrimitiveElement data = (StringPrimitiveElement) sender.onCall().next();
		String command = data.getString();
		FlowingData p = new FlowingData();
		if (_ConvertRoombaCommand.containsKey(command)) {
			p.add(new NumberPrimitiveElement.NumberPrimitiveData(_ConvertRoombaCommand.getCommand(command)));
		}
		return p;
	}

	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(StringPrimitiveElement.class);
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(NumberPrimitiveElement.class);
	}

	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		sender = senders[0];
	}

}
