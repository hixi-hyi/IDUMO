package com.hixi_hyi.idumo.common.handler;

import java.util.List;

import com.hixi_hyi.idumo.core.data.DataType;
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
	private ReceiveValidatorType vType = new ReceiveValidatorType(1,Float.class);

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
	public List<Class<?>> getDataType() throws IDUMOException {
		return DataType.generateDataType(Boolean.class);
	}

	@Override
	public IDUMOFlowingData get() {
		float d = (Float)sender.get().get(0);
		IDUMOLogManager.debug(d);
		if(condition > d){
			return IDUMOFlowingData.generatePipeData(new Boolean(true));
		}
		return IDUMOFlowingData.generatePipeData(new Boolean(false));
	}
}
