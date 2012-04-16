package com.hixi_hyi.idumo.core.validator;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public class ReceiveValidatorSize implements ReceiveValidator {

	private int size;
	public ReceiveValidatorSize(int size) {
		this.size = size;
	}

	@Override
	public void validate(IDUMOSender...senders) throws IDUMOException{
		if(senders.length==size){
			return;
		}
		throw new IDUMOException();
	}
}
