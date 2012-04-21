package com.hixi_hyi.idumo.core.exec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOPart;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public class IDUMOContainer {

	private ArrayList<IDUMOPart> items = new ArrayList<IDUMOPart>();
	private ArrayList<IDUMORunnable> runnables = new ArrayList<IDUMORunnable>();
	private ArrayList<IDUMOController> controllers = new ArrayList<IDUMOController>();

	private HashMap<IDUMOReceiver, Connect> connector = new HashMap<IDUMOReceiver, Connect>();

	public void add(IDUMOPart item) {
		items.add(item);
		if (item instanceof IDUMOController) {
			controllers.add((IDUMOController) item);
		}
		if (item instanceof IDUMORunnable) {
			runnables.add((IDUMORunnable) item);
		}
	}

	public void connect(IDUMOSender sender, IDUMOReceiver receiver) {
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
		for (Map.Entry<IDUMOReceiver, Connect> entry : connector.entrySet()) {
			IDUMOReceiver receiver = entry.getKey();
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
		private ArrayList<IDUMOSender> senders = new ArrayList<IDUMOSender>();

		public void add(IDUMOSender sender) {
			senders.add(sender);
		}

		public IDUMOSender[] getSenders() {
			return senders.toArray(new IDUMOSender[0]);
		}
	}

}
