package com.hixi_hyi.idumo.core.validator;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.Sender;

public class ReceiveValidatorSize implements ReceiveValidator {

	private int size;
	public ReceiveValidatorSize(int size) {
		this.size = size;
	}

	@Override
	public void validate(Sender...senders) throws IdumoException{
		if(senders.length==size){
			return;
		}
		throw new IdumoException();
	}
}
