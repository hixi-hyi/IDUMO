package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.common.handler._ConvertRommbaCommandHandler;
import com.hixi_hyi.idumo.common.provider._ReceiveTCPProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor._SerialSendReceiptor;
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
		_ReceiveTCPProvider provider = new _ReceiveTCPProvider(10000);
		add(provider);
		_ConvertRommbaCommandHandler converter = new _ConvertRommbaCommandHandler();
		add(converter);
		_SerialSendReceiptor serial = new _SerialSendReceiptor("/dev/stdout");
		// _SerialSendReceiptor serial = new
		// _SerialSendReceiptor("/dev/tty.ESD200v117-0CC2EC-Gener");
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
