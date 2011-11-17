package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.console.*;
import com.hixi_hyi.idumo.console.exec.*;
import com.hixi_hyi.idumo.console.provider.*;
import com.hixi_hyi.idumo.console.handler.*;
import com.hixi_hyi.idumo.console.receiptor.*;
import com.hixi_hyi.idumo.console.util.ConsoleLogger;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.hixi_hyi.idumo.common.provider.*;
import com.hixi_hyi.idumo.common.handler.*;
import com.hixi_hyi.idumo.common.receiptor.*;

public class TCP2SimpleRommbaMain extends AbstractConsoleMain {
	@Override
	public void init() {
		setExecutionWithComponent(new TCP2SimpleRommbaComponent());
	}

	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		TCP2SimpleRommbaMain main = new TCP2SimpleRommbaMain();
		main.exec();
	}
}

class TCP2SimpleRommbaComponent extends AbstractExecutionComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
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
