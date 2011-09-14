package com.hixi_hyi.idumo.console.receiptor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.Sender;

/**
 * Systemoutに出力するReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class SystemOutReceiptor implements Receiver, IdumoRunnable {
	
	private ArrayList<Sender>	senders;
	
	public SystemOutReceiptor() {
		senders = new ArrayList<Sender>();
	}
	
	@Override
	public void run() {
		for (Sender sender : senders) {
			if (!sender.isReady()) {
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Sender sender : senders) {
			for (Object o : sender.getData()) {
				sb.append(o.toString());
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	@Override
	public boolean setSender(Sender... handler) {
		senders.clear();
		for (Sender s : handler) {
			senders.add(s);
		}
		return true;
	}
	
	@Override
	public boolean isReady() {
		if (senders.size() == 0) {
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
