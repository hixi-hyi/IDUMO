package com.hixi_hyi.idumo.core.validator;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public interface ReceiveValidator {
	public void validate(IDUMOSender...senders) throws IDUMOException;
}
