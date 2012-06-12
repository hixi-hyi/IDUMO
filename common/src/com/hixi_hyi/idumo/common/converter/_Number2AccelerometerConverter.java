package com.hixi_hyi.idumo.common.converter;

import java.util.ArrayList;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.PrimitiveDataNumber;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidator;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorType;

public class _Number2AccelerometerConverter implements IDUMOSendable, IDUMOReceivable {
	
	private ArrayList<IDUMOSendable>	sender	= new ArrayList<IDUMOSendable>();
	private IDUMOReceiveValidator			vSize	= new IDUMOReceiveValidatorSize(3);
	private IDUMOReceiveValidator			vType1	= new IDUMOReceiveValidatorType(1, PrimitiveDataNumber.class);
	private IDUMOReceiveValidator			vType2	= new IDUMOReceiveValidatorType(2, PrimitiveDataNumber.class);
	private IDUMOReceiveValidator			vType3	= new IDUMOReceiveValidatorType(3, PrimitiveDataNumber.class);
	
	public _Number2AccelerometerConverter() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		for (IDUMOSendable s : sender) {
			flag = flag && (s != null) && s.isReady();
		}
		return flag;
	}
	
	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		vType3.validate(senders);
		for (IDUMOSendable s : senders) {
			sender.add(s);
		}
	}
	
	@Override
	public ConnectDataType receivableType() {
		return null;
	}
	
	@Override
	public FlowingData onCall() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public ConnectDataType sendableType() {
//		return new IDUMODataTypeConnectSingle(AndroidAccelerometerData.class);
		return null;
	}
	
}
