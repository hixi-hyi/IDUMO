package com.hixi_hyi.idumo.common.handler.raw;

import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRaw;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRawString;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.util.IDUMOLogger;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class IDUMOGetStringValueHandler implements IDUMOSendable, IDUMOReceivable {
	private String name;
	private IDUMOSendable sender;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	
	public IDUMOGetStringValueHandler(String name){
		this.name = name;
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}

	@Override
	public IDUMODataConnect receivableType() {
		return new IDUMODataConnectSingle(IDUMOData.class);
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMODataTypeRawString s = (IDUMODataTypeRawString) sender.onCall().next().get(name);
//		IDUMOLogManager.debug(s);
		return new IDUMODataFlowing(new IDUMOStringData(s));
	}

	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOStringData.class);
	}

}
