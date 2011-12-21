package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.ReceiverWithInputSize;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.PipeData;

public class StringConcatHandler implements SenderWithOption, ReceiverWithInputSize {
	
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
	private OptionMethodType	type;
	
	public StringConcatHandler(String fixWord) {
		this.fixWord = fixWord;
		this.type = Type.PREFIX;
	}
	
	@Override
	public PipeData getData() {
		// LogUtil.d();
		StringBuilder sb = new StringBuilder();
		if (type == Type.PREFIX) {
			sb.append(fixWord);
		}
		for (Object o : provider.getData()) {
			sb.append(o.toString());
		}
		if (type == Type.SUFFIX) {
			sb.append(fixWord);
		}
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
	public boolean setSender(Sender... provider) {
		if (provider.length == getInputSize()) {
			this.provider = provider[0];
			return true;
		}
		return false;
	}
	
	@Override
	public int getInputSize() {
		return 1;
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
			throw new IdumoRuntimeException();
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
