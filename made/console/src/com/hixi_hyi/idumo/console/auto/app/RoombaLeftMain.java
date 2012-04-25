package com.hixi_hyi.idumo.console.auto.app;

import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;

public class RoombaLeftMain extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new RoombaLeftComponent());
	}
	
	public static void main(String[] args) {
		RoombaLeftMain main = new RoombaLeftMain();
		main.exec();
	}
}

class RoombaLeftComponent extends IDUMOComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		StringProvider s = new StringProvider("LEFT");
		add(s);
		SendTCPReceiptor r = new SendTCPReceiptor("192.168.11.4", 10000);
		add(r);
		
		connect(s, r);
	}
	
	@Override
	public void onIdumoPrepare() {
		setLoopCount(1);
		setSleepTime(1000);
	}
}
