package com.hixi_hyi.idumo.core.validator;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.Sender;

public interface ReceiveValidator {
	public void validate(Sender...senders) throws IdumoException;
}
