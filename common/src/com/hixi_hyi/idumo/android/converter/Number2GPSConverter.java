package com.hixi_hyi.idumo.android.converter;

import java.util.ArrayList;

import com.hixi_hyi.idumo.common.data.GPSData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveNumber;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectMulti;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class Number2GPSConverter implements IDUMOSendable, IDUMOReceivable {

	private IDUMOSendable sender1;
	private IDUMOSendable sender2;

	private ReceiveValidator			vSize	= new ReceiveValidatorSize(2);
	private ReceiveValidator			vType1	= new ReceiveValidatorType(1, IDUMODataPrimitiveNumber.class);
	private ReceiveValidator			vType2	= new ReceiveValidatorType(2, IDUMODataPrimitiveNumber.class);

	public Number2GPSConverter() {}

	@Override
	public boolean isReady() {
		boolean flag = true;
		flag = flag && sender1.isReady();
		flag = flag && sender2.isReady();
		return flag;
	}

	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		sender1 = senders[0];
		sender2 = senders[1];
	}

	@Override
	public IDUMODataTypeConnect receivableType() {
		return new IDUMODataTypeConnectMulti(IDUMODataPrimitiveNumber.class,IDUMODataPrimitiveNumber.class);
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMODataPrimitiveNumber num1 = (IDUMODataPrimitiveNumber) sender1.onCall().next();
		IDUMODataPrimitiveNumber num2 = (IDUMODataPrimitiveNumber) sender2.onCall().next();
		GPSData gd = new GPSData(num1.getNumber(), num2.getNumber());
//		IDUMOLogManager.debug(gd);
		return new IDUMODataFlowing(gd);
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(GPSData.class);
	}

}
