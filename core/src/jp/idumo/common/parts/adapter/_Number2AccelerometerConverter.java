package jp.idumo.common.parts.adapter;

import java.util.ArrayList;

import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.primitive.NumberPrimitiveElement;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.validator.ReceiveValidator;
import jp.idumo.core.validator.ReceiveValidatorSize;
import jp.idumo.core.validator.ReceiveValidatorType;


//@IDUMOAdaptor(author="Hiroyoshi HOUCHI",name="Number->Accelerometer",receive={NumberPrimitiveElement.class,NumberPrimitiveElement.class},send=A)
public class _Number2AccelerometerConverter implements Sendable, Receivable {
	
	private ArrayList<Sendable> sender = new ArrayList<Sendable>();
	private ReceiveValidator vSize = new ReceiveValidatorSize(3);
	private ReceiveValidator vType1 = new ReceiveValidatorType(1, NumberPrimitiveElement.class);
	private ReceiveValidator vType2 = new ReceiveValidatorType(2, NumberPrimitiveElement.class);
	private ReceiveValidator vType3 = new ReceiveValidatorType(3, NumberPrimitiveElement.class);
	
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
	public FlowingData onCall() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return null;
	}
	
	@Override
	public ConnectDataType sendableType() {
		// return new
		// IDUMODataTypeConnectSingle(AndroidAccelerometerData.class);
		return null;
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
	
}
