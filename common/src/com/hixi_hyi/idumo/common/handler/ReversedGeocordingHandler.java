package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;

import com.hixi_hyi.idumo.common.component.ReversedGeocording;
import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
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
public class ReversedGeocordingHandler implements IDUMOSendable, IDUMOReceivable {

	private ArrayList<IDUMOSendable> senders = new ArrayList<IDUMOSendable>();
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType v1Type = new ReceiveValidatorType(1,
			IDUMONumberData.class);

	@Override
	public IDUMODataFlowing onCall() {
		double lat = ((IDUMONumberData) senders.get(0).onCall().next())
				.getNumber();
		double lon = ((IDUMONumberData) senders.get(1).onCall().next())
				.getNumber();

		ReversedGeocording rg = new ReversedGeocording(lat, lon);

		IDUMODataFlowing p = new IDUMODataFlowing();
		p.add(new IDUMOStringData(rg.getLocation()));

		return p;
	}

	@Override
	public boolean setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		v1Type.validate(senders);
		this.senders.clear();
		for (IDUMOSendable s : senders) {
			this.senders.add(s);
		}
		return true;
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
	public IDUMODataConnect receivableType() {
		return new IDUMODataConnectSingle(IDUMONumberData.class);
	}

	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOStringData.class);
	}

}
