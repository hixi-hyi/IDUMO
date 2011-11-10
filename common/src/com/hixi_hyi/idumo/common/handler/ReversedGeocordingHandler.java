package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.common.component.ReversedGeocording;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.ReceiverWithOption;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;

/**
 * 逆ジオコーディング(lat,lon->住所)のハンドラです． senderにはlat,lonの順番で設定してください．
 * 
 * @author Hiroyoshi
 * 
 */
public class ReversedGeocordingHandler implements Sender, ReceiverWithOption {
	
	private ArrayList<Sender>	senders	= new ArrayList<Sender>();
	
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
	public boolean setSender(Sender... sender) throws IdumoException {
		if (sender.length != getInputSize()) {
			throw new IdumoException();
		}
		senders.clear();
		for (Sender s : sender) {
			List<Class<?>> type = s.getDataType();
			if ((type.size() != 1) && (type.get(0) != Double.class)) {
				throw new IdumoException();
			}
			senders.add(s);
		}
		return true;
	}
	
	@Override
	public int getInputSize() {
		return 2;
	}
	
	@Override
	public boolean isReady() {
		if (senders.size() != getInputSize()) {
			return false;
		}
		for (Sender sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}
	
}
