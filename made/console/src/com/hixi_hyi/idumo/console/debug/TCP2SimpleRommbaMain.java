package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.common.handler.SimpleRommbaCommandHandler;
import com.hixi_hyi.idumo.common.provider.ReceiveTCPProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.SerialSendReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class TCP2SimpleRommbaMain extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new TCP2SimpleRommbaComponent());
	}

	public static void main(String[] args) {
		IDUMOLogManager.DEBUG = true;
		IDUMOLogManager.LOGGER = new IDUMOConsoleLogger();
		TCP2SimpleRommbaMain main = new TCP2SimpleRommbaMain();
		main.exec();
	}
}

class TCP2SimpleRommbaComponent extends IDUMOComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		ReceiveTCPProvider provider = new ReceiveTCPProvider(10000);
		add(provider);
		SimpleRommbaCommandHandler converter = new SimpleRommbaCommandHandler();
		add(converter);
		SerialSendReceiptor serial = new SerialSendReceiptor("/dev/tty.ESD200v117-0CC2EC-Gener");
		add(serial);
		connect(provider, converter);
		connect(converter,serial);
	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}
