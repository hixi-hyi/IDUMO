package com.hixi_hyi.idumo.common.converter;

import java.util.ArrayList;

import com.hixi_hyi.idumo.common.data.GPSData;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.PrimitiveDataNumber;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeMulti;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidator;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorType;

public class Number2GPSConverter implements IDUMOSendable, IDUMOReceivable {

	private IDUMOSendable sender1;
	private IDUMOSendable sender2;

	private IDUMOReceiveValidator			vSize	= new IDUMOReceiveValidatorSize(2);
	private IDUMOReceiveValidator			vType1	= new IDUMOReceiveValidatorType(1, PrimitiveDataNumber.class);
	private IDUMOReceiveValidator			vType2	= new IDUMOReceiveValidatorType(2, PrimitiveDataNumber.class);

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
	public ConnectDataType receivableType() {
		return new ConnectDataTypeMulti(PrimitiveDataNumber.class,PrimitiveDataNumber.class);
	}

	@Override
	public FlowingData onCall() {
		PrimitiveDataNumber num1 = (PrimitiveDataNumber) sender1.onCall().next();
		PrimitiveDataNumber num2 = (PrimitiveDataNumber) sender2.onCall().next();
		GPSData gd = new GPSData(num1.getNumber(), num2.getNumber());
//		IDUMOLogManager.debug(gd);
		return new FlowingData(gd);
	}

	@Override
	public ConnectDataType sendableType() {
		return new ConnectDataTypeSingle(GPSData.class);
	}

}
