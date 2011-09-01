package com.hixi_hyi.idumo.core;

import java.util.Map;

public interface SenderWithOption extends Sender {
	public Map<String,String> getOptions();
	public void setOption(OptionMethodType type) throws IdumoException;

}
