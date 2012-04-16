package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.common.handler.ConvertRommbaCommandHandler;
import com.hixi_hyi.idumo.common.provider.ReceiveTCPProvider;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.console.receiptor.SerialSendReceiptor;
import com.hixi_hyi.idumo.console.util.ConsoleLogger;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.exec.AbstractExecutionComponent;
import com.hixi_hyi.idumo.core.util.LogManager;

public class TCP2RommbaMain extends AbstractConsoleMain {
	@Override
	public void init() {
		setExecutionWithComponent(new TCP2RommbaComponent());
	}

	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		TCP2RommbaMain main = new TCP2RommbaMain();
		main.exec();
	}
}

class TCP2RommbaComponent extends AbstractExecutionComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		ReceiveTCPProvider provider = new ReceiveTCPProvider(10000);
		add(provider);
		ConvertRommbaCommandHandler converter = new ConvertRommbaCommandHandler();
		add(converter);
		SerialSendReceiptor serial = new SerialSendReceiptor("/dev/stdout");
//		SerialSendReceiptor serial = new SerialSendReceiptor("/dev/tty.ESD200v117-0CC2EC-Gener");
		add(serial);
//		ConsoleViewReceiptor receiptor = new ConsoleViewReceiptor();
//		add(receiptor);
		connect(provider, converter);
		connect(converter,serial);
//		connect(converter,receiptor);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}
