package com.hixi_hyi.idumo.console.receiptor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

/**
 * Systemoutに出力するReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class ConsoleViewReceiptor implements IDUMOReceivable, IDUMORunnable {

	private ArrayList<IDUMOSendable> senders;

	public ConsoleViewReceiptor() {
		senders = new ArrayList<IDUMOSendable>();
	}

	@Override
	public void run() {
		for (IDUMOSendable sender : senders) {
			if (!sender.isReady()) {
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (IDUMOSendable sender : senders) {
			for (Object o : sender.onCall()) {
				sb.append(o.toString());
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}

	@Override
	public boolean setSender(IDUMOSendable... handler) {
		senders.clear();
		for (IDUMOSendable s : handler) {
			senders.add(s);
		}
		return true;
	}

	@Override
	public boolean isReady() {
		if (senders.size() == 0) {
			return false;
		}
		for (IDUMOSendable sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public IDUMODataConnect receivableType() {
		return new IDUMODataConnectSingle(IDUMOData.class);
	}

}
