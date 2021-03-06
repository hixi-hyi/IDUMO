package jp.idumo.common.parts.handler;

import jp.idumo.common.annotation.IDUMOCommon;
import jp.idumo.common.component.ReversedGeocording;
import jp.idumo.common.data.element.LatLngElement;
import jp.idumo.common.data.element.LatLngElement.LatLngData;
import jp.idumo.core.annotation.IDUMOHandler;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.data.primitive.StringPrimitiveElement;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.validator.ReceiveValidatorSize;
import jp.idumo.core.validator.ReceiveValidatorType;

/**
 * 逆ジオコーディング(gps->住所)のハンドラです．
 * 
 * @author Hiroyoshi
 */
@IDUMOCommon
@IDUMOHandler(send = StringPrimitiveElement.class, receive = LatLngElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "逆ジオコーディング", summary = "LatLngから住所を調べる")
public class ReversedGeocordingHandler implements Sendable, Receivable {
	
	private Sendable				sender;
	private ReceiveValidatorSize	vSize	= new ReceiveValidatorSize(1);
	private ReceiveValidatorType	v1Type	= new ReceiveValidatorType(1, LatLngData.class);
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		LatLngElement gd = (LatLngElement) sender.onCall().next();
		
		ReversedGeocording rg = new ReversedGeocording(gd.getLatitude(), gd.getLongitude());
		
		FlowingData p = new FlowingData();
		p.add(new StringPrimitiveElement.StringPrimitiveData(rg.getLocation()));
		
		return p;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(LatLngElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(StringPrimitiveElement.class);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		v1Type.validate(senders);
		sender = senders[0];
	}
	
}
