package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.common.component.ReversedGeocording;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.ReceiverWithInputSize;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * 逆ジオコーディング(lat,lon->住所)のハンドラです． senderにはlat,lonの順番で設定してください．
 *
 * @author Hiroyoshi
 *
 */
public class ReversedGeocordingHandler implements Sender, Receiver {

	private ArrayList<Sender>	senders	= new ArrayList<Sender>();
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(2);
	private ReceiveValidatorType v1Type = new ReceiveValidatorType(1,Double.class);
	private ReceiveValidatorType v2Type = new ReceiveValidatorType(2,Double.class);

	@Override
	public PipeData getData() {
		double lat = (Double) senders.get(0).getData().get(0);
		double lon = (Double) senders.get(1).getData().get(0);

		ReversedGeocording rg = new ReversedGeocording(lat, lon);

		PipeData p = new PipeData();
		p.add(rg.getLocation());

		return p;
	}

	@Override
	public List<Class<?>> getDataType() {
		List<Class<?>> types = new ArrayList<Class<?>>();
		types.add(String.class);
		return types;
	}

	@Override
	public boolean setSender(Sender... senders) throws IdumoException {
		vSize.validate(senders);
		v1Type.validate(senders);
		v2Type.validate(senders);
		this.senders.clear();
		for(Sender s: senders){
			this.senders.add(s);
		}
		return true;
	}

	@Override
	public boolean isReady() {
		for (Sender sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}

}
