package com.hixi_hyi.idumo.core.validator;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.Sender;

public class ReceiveSizeValidator implements ReceiveValidator {
	public boolean validate(Sender[] senders,int size) throws IdumoException{
		if(senders.length==size){
			return true;
		}
		throw new IdumoException();
	}
}
