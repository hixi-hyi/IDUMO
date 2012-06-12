package com.hixi_hyi.idumo.common.provider;

import java.util.Random;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.PrimitiveDataNumber;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataTypeSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _RandomByteProvider implements IDUMOSendable {
	
	private Random	ramdom;
	
	public _RandomByteProvider() {
		ramdom = new Random();
	}
	
	@Override
	public FlowingData onCall() {
		// LogUtil.d();
		FlowingData pipes = new FlowingData();
		byte buf[] = new byte[1];
		ramdom.nextBytes(buf);
		pipes.add(new PrimitiveDataNumber(buf[0]));
		return pipes;
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new ConnectDataTypeSingle(PrimitiveDataNumber.class);
	}
	
}
