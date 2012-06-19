package com.hixi_hyi.idumo.common.parts.adapter;

import com.hixi_hyi.idumo.common.data.element.LatLngElement.LatLngData;
import com.hixi_hyi.idumo.core.annotation.IDUMOAdaptor;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.MultiConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.NumberPrimitiveElement;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

@IDUMOAdaptor(author = "Hiroyoshi HOUCHI", name = "Number->GPS", receive = { NumberPrimitiveElement.class, NumberPrimitiveElement.class }, send = LatLngData.class)
public class Number2GPSAdapter implements Sendable, Receivable {
	
	private Sendable sender1;
	private Sendable sender2;
	
	private ReceiveValidator vSize = new ReceiveValidatorSize(2);
	private ReceiveValidator vType1 = new ReceiveValidatorType(1, NumberPrimitiveElement.class);
	private ReceiveValidator vType2 = new ReceiveValidatorType(2, NumberPrimitiveElement.class);
	
	public Number2GPSAdapter() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		flag = flag && sender1.isReady();
		flag = flag && sender2.isReady();
		return flag;
	}
	
	@Override
	public FlowingData onCall() {
		NumberPrimitiveElement num1 = (NumberPrimitiveElement) sender1.onCall().next();
		NumberPrimitiveElement num2 = (NumberPrimitiveElement) sender2.onCall().next();
		LatLngData latlng = new LatLngData(num1.getNumber(), num2.getNumber());
		// IDUMOLogManager.debug(gd);
		return new FlowingData(latlng);
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new MultiConnectDataType(NumberPrimitiveElement.class, NumberPrimitiveElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(LatLngData.class);
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
