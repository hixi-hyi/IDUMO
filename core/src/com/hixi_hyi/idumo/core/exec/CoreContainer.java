package com.hixi_hyi.idumo.core.exec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Connectable;
import com.hixi_hyi.idumo.core.parts.Executable;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;

public class CoreContainer {
	
	public class Connect {
		private ArrayList<Sendable> senders = new ArrayList<Sendable>();
		
		public void add(Sendable sender) {
			senders.add(sender);
		}
		
		public Sendable[] getSenders() {
			return senders.toArray(new Sendable[0]);
		}
	}
	
	private ArrayList<Connectable> items = new ArrayList<Connectable>();
	private ArrayList<Executable> runnables = new ArrayList<Executable>();
	
	private ArrayList<CoreController> controllers = new ArrayList<CoreController>();
	
	private HashMap<Receivable, Connect> connector = new HashMap<Receivable, Connect>();
	
	public void add(Connectable item) {
		items.add(item);
		if (item instanceof CoreController) {
			controllers.add((CoreController) item);
		}
		if (item instanceof Executable) {
			runnables.add((Executable) item);
		}
	}
	
	public void connect(Sendable sender, Receivable receiver) {
		if (!connector.containsKey(receiver)) {
			Connect connect = new Connect();
			connect.add(sender);
			connector.put(receiver, connect);
		} else {
			Connect connect = connector.get(receiver);
			connect.add(sender);
		}
	}
	
	public Collection<CoreController> getApplicationControllers() {
		return controllers;
	}
	
	public Executable getRunnable() {
		return runnables.get(0);
	}
	
	public Collection<Executable> getRunnables() {
		return runnables;
	}
	
	public void setup() throws IDUMOException {
		for (Map.Entry<Receivable, Connect> entry : connector.entrySet()) {
			Receivable receiver = entry.getKey();
			Connect connect = entry.getValue();
			receiver.setSender(connect.getSenders());
		}
		
	}
	
}
