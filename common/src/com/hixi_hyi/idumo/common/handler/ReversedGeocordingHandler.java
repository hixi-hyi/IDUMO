package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;

import com.hixi_hyi.idumo.common.component.ReversedGeocording;
import com.hixi_hyi.idumo.common.data.GPSData;
import com.hixi_hyi.idumo.common.data.element.LatLngDataElement;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.PrimitiveDataNumber;
import com.hixi_hyi.idumo.core.data.PrimitiveDataString;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeMulti;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorType;

/**
 * 逆ジオコーディング(lat,lon->住所)のハンドラです． senderにはlat,lonの順番で設定してください．
 *
 * @author Hiroyoshi
 *
 */
public class ReversedGeocordingHandler implements IDUMOSendable, IDUMOReceivable {

	private IDUMOSendable sender;
	private IDUMOReceiveValidatorSize		vSize	= new IDUMOReceiveValidatorSize(1);
	private IDUMOReceiveValidatorType		v1Type	= new IDUMOReceiveValidatorType(1, GPSData.class);

	@Override
	public FlowingData onCall() {
		LatLngDataElement gd = (LatLngDataElement) sender.onCall().next();

		ReversedGeocording rg = new ReversedGeocording(gd.getLatitude(), gd.getLongitude());

		FlowingData p = new FlowingData();
		p.add(new PrimitiveDataString(rg.getLocation()));

		return p;
	}

	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		v1Type.validate(senders);
		sender = senders[0];
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public ConnectDataType receivableType() {
		return new ConnectDataTypeSingle(GPSData.class);
	}

	@Override
	public ConnectDataType sendableType() {
		return new ConnectDataTypeSingle(PrimitiveDataString.class);
	}

}
