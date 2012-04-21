package com.hixi_hyi.idumo.common.provider;

import java.util.Random;

import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class RandomByteProvider implements IDUMOSender {

	private Random ramdom;

	public RandomByteProvider() {
		ramdom = new Random();
	}

	@Override
	public IDUMOFlowingData onCall() {
		// LogUtil.d();
		IDUMOFlowingData pipes = new IDUMOFlowingData();
		byte buf[] = new byte[1];
		ramdom.nextBytes(buf);
		pipes.add(new IDUMONumberData(buf[0]));
		return pipes;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMONumberData.class;
	}

}
