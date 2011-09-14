package com.hixi_hyi.idumo.core.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class RandomByteProvider implements Sender {
	
	private Random	ramdom;
	
	public RandomByteProvider() {
		ramdom = new Random();
	}
	
	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		types.add(Byte.class);
		return types;
	}
	
	@Override
	public PipeData getData() {
		// LogUtil.d();
		PipeData pipes = new PipeData();
		byte buf[] = new byte[1];
		ramdom.nextBytes(buf);
		pipes.add(buf[0]);
		return pipes;
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
}
