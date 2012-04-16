package com.hixi_hyi.idumo.core;

import java.util.Map;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public interface SenderWithOption extends IDUMOSender {
	public Map<String, String> getOptions();
	
	public void setOption(OptionMethodType type) throws IDUMOException;
	
}
