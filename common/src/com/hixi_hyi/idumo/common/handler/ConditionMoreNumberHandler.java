package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.common.data.IDUMOBoolData;
import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class ConditionMoreNumberHandler implements IDUMOSendable, IDUMOReceivable {

	private IDUMOSendable sender;
	private float condition;
	private ReceiveValidatorSize validator = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1,
			IDUMONumberData.class);

	public ConditionMoreNumberHandler(float condition) {
		this.condition = condition;
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public boolean setSender(IDUMOSendable... senders) throws IDUMOException {
		validator.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
		return true;
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMONumberData number = (IDUMONumberData) sender.onCall().next();
		double d = number.getNumber();
		IDUMOLogManager.debug(d);
		if (condition > d) {
			return new IDUMODataFlowing(new IDUMOBoolData(true));
		}
		return new IDUMODataFlowing(new IDUMOBoolData(false));
	}

	@Override
	public IDUMODataConnect receivableType() {
		return new IDUMODataConnectSingle(IDUMONumberData.class);
	}

	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOBoolData.class);
	}
}
