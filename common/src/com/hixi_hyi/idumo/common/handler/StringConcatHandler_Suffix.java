package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.ReceiverWithInputSize;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class StringConcatHandler_Suffix implements Sender,Receiver{ 
	
	public enum Type implements OptionMethodType {
		PREFIX("Get Accelerometer X"), SUFFIX("Get Accelerometer Y"), ;
		private final String	description;
		
		Type(String description) {
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}
	}
	
	private Sender				provider;
	private String				fixWord;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	
	public StringConcatHandler_Suffix(String fixWord) {
		this.fixWord = fixWord;
	}
	
	@Override
	public PipeData getData() {
		// LogUtil.d();
		StringBuilder sb = new StringBuilder();
		for (Object o : provider.getData()) {
			sb.append(o.toString());
		}
		sb.append(fixWord);
		PipeData p = new PipeData();
		p.add(sb.toString());
		return p;
	}
	
	@Override
	public List<Class<?>> getDataType() throws IdumoException {
		List<Class<?>> type = new ArrayList<Class<?>>();
		type.add(String.class);
		return type;
	}
	
	@Override
	public boolean setSender(Sender... senders) throws IdumoException {
		vSize.validate(senders);
		this.provider = senders[0];
		return true;
	}
	
	@Override
	public boolean isReady() {
		return (provider != null) && provider.isReady();
	}
	
}
