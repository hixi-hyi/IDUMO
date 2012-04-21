package com.hixi_hyi.idumo.android.converter;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.data.IDUMOAndroidAccelerometerData;
import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class Number2AccelerometerConverter implements IDUMOSender, IDUMOReceiver {
	
	private ArrayList<IDUMOSender>	sender	= new ArrayList<IDUMOSender>();
	private ReceiveValidator		vSize	= new ReceiveValidatorSize(3);
	private ReceiveValidator		vType1	= new ReceiveValidatorType(1, IDUMONumberData.class);
	private ReceiveValidator		vType2	= new ReceiveValidatorType(2, IDUMONumberData.class);
	private ReceiveValidator		vType3	= new ReceiveValidatorType(3, IDUMONumberData.class);
	
	public Number2AccelerometerConverter() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		for (IDUMOSender s : sender) {
			flag = flag && (s != null) && s.isReady();
		}
		return flag;
	}
	
	@Override
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		vType3.validate(senders);
		for (IDUMOSender s : senders) {
			sender.add(s);
		}
		return true;
	}
	
	@Override
	public Class<? extends IDUMOData> receivableType() {
		return null;
	}
	
	@Override
	public IDUMOFlowingData onCall() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMOAndroidAccelerometerData.class;
	}
	
}
