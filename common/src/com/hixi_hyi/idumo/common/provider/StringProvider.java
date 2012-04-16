package com.hixi_hyi.idumo.common.provider;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class StringProvider implements Sender {

	private String str;

	public StringProvider(String str) {
		LogManager.debug(str);
		this.str = str;
	}

	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		types.add(String.class);
		return types;
	}

	@Override
	public PipeData getData() {
		LogManager.log();
		PipeData pipes = new PipeData();
		pipes.add(str);
		return pipes;
	}

	@Override
	public boolean isReady() {
		return true;
	}

}
