package com.hixi_hyi.idumo.core.exec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOConnectable;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

public class IDUMOContainer {

	private ArrayList<IDUMOConnectable> items = new ArrayList<IDUMOConnectable>();
	private ArrayList<IDUMORunnable> runnables = new ArrayList<IDUMORunnable>();
	private ArrayList<IDUMOController> controllers = new ArrayList<IDUMOController>();

	private HashMap<IDUMOReceivable, Connect> connector = new HashMap<IDUMOReceivable, Connect>();

	public void add(IDUMOConnectable item) {
		items.add(item);
		if (item instanceof IDUMOController) {
			controllers.add((IDUMOController) item);
		}
		if (item instanceof IDUMORunnable) {
			runnables.add((IDUMORunnable) item);
		}
	}

	public void connect(IDUMOSendable sender, IDUMOReceivable receiver) {
		if (!connector.containsKey(receiver)) {
			Connect connect = new Connect();
			connect.add(sender);
			connector.put(receiver, connect);
		} else {
			Connect connect = connector.get(receiver);
			connect.add(sender);
		}
	}

	public void setup() throws IDUMOException {
		for (Map.Entry<IDUMOReceivable, Connect> entry : connector.entrySet()) {
			IDUMOReceivable receiver = entry.getKey();
			Connect connect = entry.getValue();
			receiver.setSender(connect.getSenders());
		}

	}

	public Collection<IDUMORunnable> getRunnables() {
		return runnables;
	}

	public IDUMORunnable getRunnable() {
		return runnables.get(0);
	}

	public Collection<IDUMOController> getApplicationControllers() {
		return controllers;
	}

	public class Connect {
		private ArrayList<IDUMOSendable> senders = new ArrayList<IDUMOSendable>();

		public void add(IDUMOSendable sender) {
			senders.add(sender);
		}

		public IDUMOSendable[] getSenders() {
			return senders.toArray(new IDUMOSendable[0]);
		}
	}

}
