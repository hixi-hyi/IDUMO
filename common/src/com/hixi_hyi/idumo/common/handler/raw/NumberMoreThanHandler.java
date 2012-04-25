package com.hixi_hyi.idumo.common.handler.raw;

import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveBool;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveNumber;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class NumberMoreThanHandler implements IDUMOSendable, IDUMOReceivable {

	private IDUMOSendable			sender;
	private double					condition;
	private ReceiveValidatorSize	validator	= new ReceiveValidatorSize(1);
	private ReceiveValidatorType	vType		= new ReceiveValidatorType(1, IDUMODataPrimitiveNumber.class);

	public NumberMoreThanHandler(double condition) {
		this.condition = condition;
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		validator.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMODataPrimitiveNumber number = (IDUMODataPrimitiveNumber) sender.onCall().next();
		double d = number.getNumber();
		// IDUMOLogManager.debug(d);
		// IDUMOLogManager.debug(String.format("raw:%.0f,con:%.0f",d,condition));
		if (condition < d) {
			return new IDUMODataFlowing(new IDUMODataPrimitiveBool(true));
		}
		return new IDUMODataFlowing(new IDUMODataPrimitiveBool(false));
	}

	@Override
	public IDUMODataTypeConnect receivableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveNumber.class);
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveBool.class);
	}
}
