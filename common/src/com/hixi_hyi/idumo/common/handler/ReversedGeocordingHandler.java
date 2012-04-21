package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;

import com.hixi_hyi.idumo.common.component.ReversedGeocording;
import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * 逆ジオコーディング(lat,lon->住所)のハンドラです． senderにはlat,lonの順番で設定してください．
 * 
 * @author Hiroyoshi
 * 
 */
public class ReversedGeocordingHandler implements IDUMOSender, IDUMOReceiver {

	private ArrayList<IDUMOSender> senders = new ArrayList<IDUMOSender>();
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType v1Type = new ReceiveValidatorType(1,
			IDUMONumberData.class);

	@Override
	public IDUMOFlowingData onCall() {
		double lat = ((IDUMONumberData) senders.get(0).onCall().next())
				.getNumber();
		double lon = ((IDUMONumberData) senders.get(1).onCall().next())
				.getNumber();

		ReversedGeocording rg = new ReversedGeocording(lat, lon);

		IDUMOFlowingData p = new IDUMOFlowingData();
		p.add(new IDUMOStringData(rg.getLocation()));

		return p;
	}

	@Override
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		vSize.validate(senders);
		v1Type.validate(senders);
		this.senders.clear();
		for (IDUMOSender s : senders) {
			this.senders.add(s);
		}
		return true;
	}

	@Override
	public boolean isReady() {
		for (IDUMOSender sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Class<? extends IDUMOData> receivableType() {
		return IDUMONumberData.class;
	}

	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMOStringData.class;
	}

}
