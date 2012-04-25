package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.common.provider._RandomByteProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.receiptor._SerialSendReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;

public class SerialSendMain extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new SerialSendComponent());
	}

	public static void main(String[] args) {
		SerialSendMain main = new SerialSendMain();
		main.exec();
	}
}

class SerialSendComponent extends IDUMOComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		_RandomByteProvider provider = new _RandomByteProvider();
		add(provider);
		_SerialSendReceiptor serial = new _SerialSendReceiptor("/dev/stdout");
		add(serial);

		connect(provider, serial);
	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}
