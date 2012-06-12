package com.hixi_hyi.idumo.common.converter;

import com.hixi_hyi.idumo.common.data.GPSData;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.MultiConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.NumberPrimitiveData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class Number2GPSConverter implements Sendable, Receivable {
	
	private Sendable sender1;
	private Sendable sender2;
	
	private ReceiveValidator vSize = new ReceiveValidatorSize(2);
	private ReceiveValidator vType1 = new ReceiveValidatorType(1, NumberPrimitiveData.class);
	private ReceiveValidator vType2 = new ReceiveValidatorType(2, NumberPrimitiveData.class);
	
	public Number2GPSConverter() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		flag = flag && sender1.isReady();
		flag = flag && sender2.isReady();
		return flag;
	}
	
	@Override
	public FlowingData onCall() {
		NumberPrimitiveData num1 = (NumberPrimitiveData) sender1.onCall().next();
		NumberPrimitiveData num2 = (NumberPrimitiveData) sender2.onCall().next();
		GPSData gd = new GPSData(num1.getNumber(), num2.getNumber());
		// IDUMOLogManager.debug(gd);
		return new FlowingData(gd);
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new MultiConnectDataType(NumberPrimitiveData.class, NumberPrimitiveData.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(GPSData.class);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		sender1 = senders[0];
		sender2 = senders[1];
	}
	
}
