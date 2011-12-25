package com.hixi_hyi.idumo.core.validator;

import java.util.List;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.Sender;

public class ReceiveValidatorType implements ReceiveValidator {

	private int num ;
	private Class<?>[] classes;

	public ReceiveValidatorType(int num,Class<?>...classes) {
		this.num = num-1;
		this.classes = classes;
	}

	@Override
	public void validate(Sender...senders) throws IdumoException{
		List<Class<?>> type = senders[num].getDataType();
		if(type.size()!=classes.length){
			throw new IdumoException();
		}
		for(int i=0;i<type.size();i++){
			if(classes[i]!=type.get(i)){
				throw new IdumoException();
			}
		}
		return;
	}

}
