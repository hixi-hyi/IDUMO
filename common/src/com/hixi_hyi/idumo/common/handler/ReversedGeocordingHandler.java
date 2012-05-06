package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;

import com.hixi_hyi.idumo.common.component.ReversedGeocording;
import com.hixi_hyi.idumo.common.data.GPSData;
import com.hixi_hyi.idumo.common.data.element.LatLngDataElement;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveNumber;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveString;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectMulti;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
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
	public IDUMODataFlowing onCall() {
		LatLngDataElement gd = (LatLngDataElement) sender.onCall().next();

		ReversedGeocording rg = new ReversedGeocording(gd.getLatitude(), gd.getLongitude());

		IDUMODataFlowing p = new IDUMODataFlowing();
		p.add(new IDUMODataPrimitiveString(rg.getLocation()));

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
	public IDUMODataTypeConnect receivableType() {
		return new IDUMODataTypeConnectSingle(GPSData.class);
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveString.class);
	}

}
