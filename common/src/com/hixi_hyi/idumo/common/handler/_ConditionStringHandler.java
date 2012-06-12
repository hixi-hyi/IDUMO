package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.PrimitiveDataBool;
import com.hixi_hyi.idumo.core.data.PrimitiveDataString;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorType;

public class _ConditionStringHandler implements IDUMOSendable, IDUMOReceivable {
	
	private IDUMOSendable			sender;
	private String					condition;
	private IDUMOReceiveValidatorSize	validator	= new IDUMOReceiveValidatorSize(1);
	private IDUMOReceiveValidatorType	vType		= new IDUMOReceiveValidatorType(1, PrimitiveDataString.class);
	
	public _ConditionStringHandler(String condition) {
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
	public FlowingData onCall() {
		PrimitiveDataString data = (PrimitiveDataString) sender.onCall().next();
		String str = data.getString();
		if (condition.equals(str)) {
			return new FlowingData(new PrimitiveDataBool(true));
		}
		return new FlowingData(new PrimitiveDataBool(false));
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new ConnectDataTypeSingle(PrimitiveDataString.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new ConnectDataTypeSingle(PrimitiveDataBool.class);
	}
}
