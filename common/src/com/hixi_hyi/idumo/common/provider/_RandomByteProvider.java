package com.hixi_hyi.idumo.common.provider;

import java.util.Random;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.NumberPrimitiveData;
import com.hixi_hyi.idumo.core.parts.Sendable;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _RandomByteProvider implements Sendable {
	
	private Random ramdom;
	
	public _RandomByteProvider() {
		ramdom = new Random();
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public FlowingData onCall() {
		// LogUtil.d();
		FlowingData pipes = new FlowingData();
		byte buf[] = new byte[1];
		ramdom.nextBytes(buf);
		pipes.add(new NumberPrimitiveData(buf[0]));
		return pipes;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(NumberPrimitiveData.class);
	}
	
}
