package com.hixi_hyi.idumo.common.provider;

import java.util.Random;

import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveNumber;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _RandomByteProvider implements IDUMOSendable {

	private Random ramdom;

	public _RandomByteProvider() {
		ramdom = new Random();
	}

	@Override
	public IDUMODataFlowing onCall() {
		// LogUtil.d();
		IDUMODataFlowing pipes = new IDUMODataFlowing();
		byte buf[] = new byte[1];
		ramdom.nextBytes(buf);
		pipes.add(new IDUMODataPrimitiveNumber(buf[0]));
		return pipes;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveNumber.class);
	}

}
