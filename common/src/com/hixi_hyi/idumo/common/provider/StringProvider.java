package com.hixi_hyi.idumo.common.provider;

import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class StringProvider implements IDUMOSender {

	private String str;

	public StringProvider(String str) {
		IDUMOLogManager.debug(str);
		this.str = str;
	}

	@Override
	public IDUMOFlowingData onCall() {
		IDUMOLogManager.log();
		IDUMOFlowingData pipes = new IDUMOFlowingData();
		pipes.add(new IDUMOStringData(str));
		return pipes;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMOStringData.class;
	}

}
