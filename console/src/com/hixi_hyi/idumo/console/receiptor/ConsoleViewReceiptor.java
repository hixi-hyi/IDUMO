package com.hixi_hyi.idumo.console.receiptor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

/**
 * Systemoutに出力するReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class ConsoleViewReceiptor implements IDUMOReceiver, IDUMORunnable {
	
	private ArrayList<IDUMOSender>	senders;
	
	public ConsoleViewReceiptor() {
		senders = new ArrayList<IDUMOSender>();
	}
	
	@Override
	public void run() {
		for (IDUMOSender sender : senders) {
			if (!sender.isReady()) {
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (IDUMOSender sender : senders) {
			for (Object o : sender.get()) {
				sb.append(o.toString());
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	@Override
	public boolean setSender(IDUMOSender... handler) {
		senders.clear();
		for (IDUMOSender s : handler) {
			senders.add(s);
		}
		return true;
	}
	
	@Override
	public boolean isReady() {
		if (senders.size() == 0) {
			return false;
		}
		for (IDUMOSender sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}
	
}
