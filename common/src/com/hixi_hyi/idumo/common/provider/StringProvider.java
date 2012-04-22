package com.hixi_hyi.idumo.common.provider;

import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class StringProvider implements IDUMOSendable {

	private String str;

	public StringProvider(String str) {
		IDUMOLogManager.debug(str);
		this.str = str;
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMOLogManager.log();
		IDUMODataFlowing pipes = new IDUMODataFlowing();
		pipes.add(new IDUMOStringData(str));
		return pipes;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOStringData.class);
	}

}
