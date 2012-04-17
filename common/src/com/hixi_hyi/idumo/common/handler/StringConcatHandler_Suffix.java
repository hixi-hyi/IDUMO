package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class StringConcatHandler_Suffix implements IDUMOSender,IDUMOReceiver{ 
	
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
	
	private IDUMOSender				provider;
	private String				fixWord;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	
	public StringConcatHandler_Suffix(String fixWord) {
		this.fixWord = fixWord;
	}
	
	@Override
	public IDUMOFlowingData get() {
		// LogUtil.d();
		StringBuilder sb = new StringBuilder();
		for (Object o : provider.get()) {
			sb.append(o.toString());
		}
		sb.append(fixWord);
		IDUMOFlowingData p = new IDUMOFlowingData();
		p.add(sb.toString());
		return p;
	}
	
	@Override
	public List<Class<?>> getDataType() throws IDUMOException {
		List<Class<?>> type = new ArrayList<Class<?>>();
		type.add(String.class);
		return type;
	}
	
	@Override
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		vSize.validate(senders);
		this.provider = senders[0];
		return true;
	}
	
	@Override
	public boolean isReady() {
		return (provider != null) && provider.isReady();
	}
	
}
