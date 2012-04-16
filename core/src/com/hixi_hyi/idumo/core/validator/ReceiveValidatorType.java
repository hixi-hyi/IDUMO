package com.hixi_hyi.idumo.core.validator;

import java.util.List;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public class ReceiveValidatorType implements ReceiveValidator {

	private int num ;
	private Class<?>[] classes;

	public ReceiveValidatorType(int num,Class<?>...classes) {
		this.num = num-1;
		this.classes = classes;
	}

	@Override
	public void validate(IDUMOSender...senders) throws IDUMOException{
		List<Class<?>> type = senders[num].getDataType();
		if(type.size()!=classes.length){
			throw new IDUMOException();
		}
		for(int i=0;i<type.size();i++){
			if(classes[i]!=type.get(i)){
				throw new IDUMOException();
			}
		}
		return;
	}

}
