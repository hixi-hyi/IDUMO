package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.common.handler.ConvertRommbaCommandHandler;
import com.hixi_hyi.idumo.common.provider.ReceiveTCPProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.SerialSendReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class TCP2RommbaMain extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new TCP2RommbaComponent());
	}

	public static void main(String[] args) {
		IDUMOLogManager.DEBUG = true;
		IDUMOLogManager.LOGGER = new IDUMOConsoleLogger();
		TCP2RommbaMain main = new TCP2RommbaMain();
		main.exec();
	}
}

class TCP2RommbaComponent extends IDUMOComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		ReceiveTCPProvider provider = new ReceiveTCPProvider(10000);
		add(provider);
		ConvertRommbaCommandHandler converter = new ConvertRommbaCommandHandler();
		add(converter);
		SerialSendReceiptor serial = new SerialSendReceiptor("/dev/stdout");
		// SerialSendReceiptor serial = new
		// SerialSendReceiptor("/dev/tty.ESD200v117-0CC2EC-Gener");
		add(serial);
		// ConsoleViewReceiptor receiptor = new ConsoleViewReceiptor();
		// add(receiptor);
		connect(provider, converter);
		connect(converter, serial);
		// connect(converter,receiptor);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}
