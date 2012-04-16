package com.hixi_hyi.idumo.common.handler;

import java.util.List;

import com.hixi_hyi.idumo.core.data.DataType;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class ConditionStringHandler implements IDUMOSender, IDUMOReceiver {

	private IDUMOSender sender;
	private String condition;
	private ReceiveValidatorSize validator = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1,String.class);

	public ConditionStringHandler(String condition) {
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
	public PipeData getData() {
		String str = (String)sender.getData().get(0);
		if(condition.equals(str)){
			return PipeData.generatePipeData(new Boolean(true));
		}
		return PipeData.generatePipeData(new Boolean(false));
	}
}
