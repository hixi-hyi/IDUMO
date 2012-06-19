package com.hixi_hyi.idumo.common.parts.handler;

import com.hixi_hyi.idumo.common.component.ReversedGeocording;
import com.hixi_hyi.idumo.common.data.element.LatLngElement;
import com.hixi_hyi.idumo.common.data.element.LatLngElement.LatLngData;
import com.hixi_hyi.idumo.core.annotation.IDUMOHandler;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.StringPrimitiveElement;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * 逆ジオコーディング(lat,lon->住所)のハンドラです． senderにはlat,lonの順番で設定してください．
 *
 * @author Hiroyoshi
 *
 */
@IDUMOHandler(author = "Hiroyoshi HOUCHI", name = "逆ジオコーディング", description = "逆ジオコーディングを行うハンドラ．LatLngElementを受け取りStringを返します．", send = StringPrimitiveElement.class, receive = LatLngElement.class)
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
