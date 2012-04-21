package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.common.handler.ConvertRommbaCommandHandler;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.receiptor.SerialSendReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;

public class ReceiveConsoleMain extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new ReceiveConsoleComponent());
	}

	public static void main(String[] args) {
		ReceiveConsoleMain main = new ReceiveConsoleMain();
		main.exec();
	}
}

class ReceiveConsoleComponent extends IDUMOComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		StringProvider provider = new StringProvider("CLEAN");
		add(provider);
		ConvertRommbaCommandHandler converter = new ConvertRommbaCommandHandler();
		add(converter);
		// ConsoleViewReceiptor console = new ConsoleViewReceiptor();
		// add(console);
		SerialSendReceiptor serial = new SerialSendReceiptor("/dev/stdout");
		add(serial);

		connect(provider, converter);
		connect(converter, serial);
		// connect(converter,console);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}
