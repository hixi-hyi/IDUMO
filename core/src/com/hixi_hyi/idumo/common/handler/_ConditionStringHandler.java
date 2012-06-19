package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.BoolPrimitiveElement;
import com.hixi_hyi.idumo.core.data.primitive.StringPrimitiveElement;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class _ConditionStringHandler implements Sendable, Receivable {

	private Sendable sender;
	private String condition;
	private ReceiveValidatorSize validator = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1, StringPrimitiveElement.class);

	public _ConditionStringHandler(String condition) {
		this.condition = condition;
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public FlowingData onCall() {
		StringPrimitiveElement data = (StringPrimitiveElement) sender.onCall().next();
		String str = data.getString();
		if (condition.equals(str)) {
			return new FlowingData(new BoolPrimitiveElement.BoolPrimitiveData(true));
		}
		return new FlowingData(new BoolPrimitiveElement.BoolPrimitiveData(false));
	}

	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(StringPrimitiveElement.class);
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(BoolPrimitiveElement.class);
	}

	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		validator.validate(senders);
		vType.validate(senders);
		sender = senders[0];
	}
}
