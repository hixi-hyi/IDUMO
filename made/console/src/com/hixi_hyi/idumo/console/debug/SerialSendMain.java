package com.hixi_hyi.idumo.console.debug;

import com.hixi_hyi.idumo.common.provider.RandomByteProvider;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.console.receiptor.SerialSendReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.exec.AbstractExecutionComponent;

public class SerialSendMain extends AbstractConsoleMain {
	@Override
	public void init() {
		setExecutionWithComponent(new SerialSendComponent());
	}
	
	public static void main(String[] args) {
		SerialSendMain main = new SerialSendMain();
		main.exec();
	}
}

class SerialSendComponent extends AbstractExecutionComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		RandomByteProvider provider = new RandomByteProvider();
		add(provider);
		SerialSendReceiptor serial = new SerialSendReceiptor("/dev/stdout");
		add(serial);
		
		connect(provider, serial);
	}
	
	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}
}
