package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class StringConcatHandler implements SenderWithOption, IDUMOReceiver {
	
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
	private OptionMethodType	type;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	
	public StringConcatHandler(String fixWord) {
		this.fixWord = fixWord;
		this.type = Type.PREFIX;
	}
	
	@Override
	public IDUMOFlowingData get() {
		// LogUtil.d();
		StringBuilder sb = new StringBuilder();
		if (type == Type.PREFIX) {
			sb.append(fixWord);
		}
		for (Object o : provider.get()) {
			sb.append(o.toString());
		}
		if (type == Type.SUFFIX) {
			sb.append(fixWord);
		}
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
	
	@Override
	public void setOption(OptionMethodType type) {
		if (type instanceof Type) {
			this.type = type;
		} else {
			throw new IDUMORuntimeException();
		}
	}
	
	@Override
	public Map<String, String> getOptions() {
		Map<String, String> map = new HashMap<String, String>();
		for (OptionMethodType t : Type.values()) {
			map.put(t.toString(), t.getDescription());
		}
		return map;
	}
	
}
