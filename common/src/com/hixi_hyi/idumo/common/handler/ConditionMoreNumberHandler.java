package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.common.data.IDUMOBoolData;
import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class ConditionMoreNumberHandler implements IDUMOSender, IDUMOReceiver {

	private IDUMOSender sender;
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
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		validator.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
		return true;
	}

	@Override
	public IDUMOFlowingData onCall() {
		IDUMONumberData number = (IDUMONumberData) sender.onCall().next();
		double d = number.getNumber();
		IDUMOLogManager.debug(d);
		if (condition > d) {
			return IDUMOFlowingData.generatePipeData(new IDUMOBoolData(true));
		}
		return IDUMOFlowingData.generatePipeData(new IDUMOBoolData(false));
	}

	@Override
	public Class<? extends IDUMOData> receivableType() {
		return IDUMONumberData.class;
	}

	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMOBoolData.class;
	}
}
