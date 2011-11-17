package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.console.*;
import com.hixi_hyi.idumo.console.exec.*;
import com.hixi_hyi.idumo.console.provider.*;
import com.hixi_hyi.idumo.console.handler.*;
import com.hixi_hyi.idumo.console.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.common.provider.*;
import com.hixi_hyi.idumo.common.handler.*;
import com.hixi_hyi.idumo.common.receiptor.*;

public class ReceiveConsoleMain extends AbstractConsoleMain {
	@Override
	public void init() {
		setExecutionWithComponent(new ReceiveConsoleComponent());
	}

	public static void main(String[] args) {
		ReceiveConsoleMain main = new ReceiveConsoleMain();
		main.exec();
	}
}

class ReceiveConsoleComponent extends AbstractExecutionComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		StringProvider provider = new StringProvider("CLEAN");
		add(provider);
		ConvertRommbaCommandHandler converter = new ConvertRommbaCommandHandler();
		add(converter);
//		ConsoleViewReceiptor console = new ConsoleViewReceiptor();
//		add(console);
		SerialSendReceiptor serial = new SerialSendReceiptor("/dev/stdout");
		add(serial);

		connect(provider, converter);
		connect(converter,serial);
//		connect(converter,console);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}
