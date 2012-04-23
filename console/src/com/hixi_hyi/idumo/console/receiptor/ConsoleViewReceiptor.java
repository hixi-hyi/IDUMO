package com.hixi_hyi.idumo.console.receiptor;

import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRawString;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.util.IDUMOLogger;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

/**
 * Systemoutに出力するReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class ConsoleViewReceiptor implements IDUMOReceivable, IDUMORunnable {

	private IDUMOSendable sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);

	@Override
	public void run() {
		IDUMODataFlowing flowdata =  sender.onCall();
		IDUMOStringData data = (IDUMOStringData) flowdata.next();
		System.out.println(data.getString());
	}

	@Override
	public void setSender(IDUMOSendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public IDUMODataConnect receivableType() {
		return new IDUMODataConnectSingle(IDUMOData.class);
	}

}
