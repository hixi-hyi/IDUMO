package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;

import com.hixi_hyi.idumo.common.component._ReversedGeocording;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveNumber;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveString;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * 逆ジオコーディング(lat,lon->住所)のハンドラです． senderにはlat,lonの順番で設定してください．
 * 
 * @author Hiroyoshi
 * 
 */
public class _ReversedGeocordingHandler implements IDUMOSendable,
		IDUMOReceivable {

	private ArrayList<IDUMOSendable> senders = new ArrayList<IDUMOSendable>();
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType v1Type = new ReceiveValidatorType(1,
			IDUMODataPrimitiveNumber.class);

	@Override
	public IDUMODataFlowing onCall() {
		double lat = ((IDUMODataPrimitiveNumber) senders.get(0).onCall().next())
				.getNumber();
		double lon = ((IDUMODataPrimitiveNumber) senders.get(1).onCall().next())
				.getNumber();

		_ReversedGeocording rg = new _ReversedGeocording(lat, lon);

		IDUMODataFlowing p = new IDUMODataFlowing();
		p.add(new IDUMODataPrimitiveString(rg.getLocation()));

		return p;
	}

	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		v1Type.validate(senders);
		this.senders.clear();
		for (IDUMOSendable s : senders) {
			this.senders.add(s);
		}
	}

	@Override
	public boolean isReady() {
		for (IDUMOSendable sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public IDUMODataTypeConnect receivableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveNumber.class);
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveString.class);
	}

}
