package com.hixi_hyi.idumo.android.converter;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.data.IDUMOAndroidAccelerometerData;
import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class Number2AccelerometerConverter implements IDUMOSendable, IDUMOReceivable {
	
	private ArrayList<IDUMOSendable>	sender	= new ArrayList<IDUMOSendable>();
	private ReceiveValidator		vSize	= new ReceiveValidatorSize(3);
	private ReceiveValidator		vType1	= new ReceiveValidatorType(1, IDUMONumberData.class);
	private ReceiveValidator		vType2	= new ReceiveValidatorType(2, IDUMONumberData.class);
	private ReceiveValidator		vType3	= new ReceiveValidatorType(3, IDUMONumberData.class);
	
	public Number2AccelerometerConverter() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		for (IDUMOSendable s : sender) {
			flag = flag && (s != null) && s.isReady();
		}
		return flag;
	}
	
	@Override
	public boolean setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		vType3.validate(senders);
		for (IDUMOSendable s : senders) {
			sender.add(s);
		}
		return true;
	}
	
	@Override
	public IDUMODataConnect receivableType() {
		return null;
	}
	
	@Override
	public IDUMODataFlowing onCall() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOAndroidAccelerometerData.class);
	}
	
}
