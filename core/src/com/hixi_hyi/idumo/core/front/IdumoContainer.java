package com.hixi_hyi.idumo.core.front;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.hixi_hyi.idumo.core.ApplicationController;
import com.hixi_hyi.idumo.core.IdumoComponent;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.Sender;

public class IdumoContainer {
	
	private ArrayList<IdumoComponent>			items		= new ArrayList<IdumoComponent>();
	private ArrayList<IdumoRunnable>			runnables	= new ArrayList<IdumoRunnable>();
	private ArrayList<ApplicationController>	controllers	= new ArrayList<ApplicationController>();
	
	private HashMap<Receiver, Connect>			connector	= new HashMap<Receiver, Connect>();
	
	public void add(IdumoComponent item) {
		items.add(item);
		if (item instanceof ApplicationController) {
			controllers.add((ApplicationController) item);
		}
		if (item instanceof IdumoRunnable) {
			runnables.add((IdumoRunnable) item);
		}
	}
	
	public void connect(Sender sender, Receiver receiver) {
		if (!connector.containsKey(receiver)) {
			Connect connect = new Connect();
			connect.add(sender);
			connector.put(receiver, connect);
		} else {
			Connect connect = connector.get(receiver);
			connect.add(sender);
		}
	}
	
	public void compile() throws IdumoException {
		for (Map.Entry<Receiver, Connect> entry : connector.entrySet()) {
			Receiver receiver = entry.getKey();
			Connect connect = entry.getValue();
			receiver.setSender(connect.getSenders());
		}
		
	}
	
	public Collection<IdumoRunnable> getRunnables() {
		return runnables;
	}
	
	public Collection<ApplicationController> getController() {
		return controllers;
	}
	
	public class Connect {
		private ArrayList<Sender>	senders	= new ArrayList<Sender>();
		
		public void add(Sender sender) {
			senders.add(sender);
		}
		
		public Sender[] getSenders() {
			return senders.toArray(new Sender[0]);
		}
	}
	
}
