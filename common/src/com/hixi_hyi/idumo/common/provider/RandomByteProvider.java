package com.hixi_hyi.idumo.common.provider;

import java.util.Random;

import com.hixi_hyi.idumo.common.data.IDUMONumberData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class RandomByteProvider implements IDUMOSendable {

	private Random ramdom;

	public RandomByteProvider() {
		ramdom = new Random();
	}

	@Override
	public IDUMODataFlowing onCall() {
		// LogUtil.d();
		IDUMODataFlowing pipes = new IDUMODataFlowing();
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
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMONumberData.class);
	}

}
