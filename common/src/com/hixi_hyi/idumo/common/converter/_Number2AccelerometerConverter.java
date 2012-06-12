package com.hixi_hyi.idumo.common.converter;

import java.util.ArrayList;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.NumberPrimitiveData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class _Number2AccelerometerConverter implements Sendable, Receivable {
	
	private ArrayList<Sendable>	sender	= new ArrayList<Sendable>();
	private ReceiveValidator			vSize	= new ReceiveValidatorSize(3);
	private ReceiveValidator			vType1	= new ReceiveValidatorType(1, NumberPrimitiveData.class);
	private ReceiveValidator			vType2	= new ReceiveValidatorType(2, NumberPrimitiveData.class);
	private ReceiveValidator			vType3	= new ReceiveValidatorType(3, NumberPrimitiveData.class);
	
	public _Number2AccelerometerConverter() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		for (Sendable s : sender) {
			flag = flag && (s != null) && s.isReady();
		}
		return flag;
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		vType3.validate(senders);
		for (Sendable s : senders) {
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
