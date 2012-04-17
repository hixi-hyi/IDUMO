package com.hixi_hyi.idumo.common.provider;

import java.util.ArrayList;
import java.util.List;

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
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		types.add(String.class);
		return types;
	}

	@Override
	public IDUMOFlowingData get() {
		IDUMOLogManager.log();
		IDUMOFlowingData pipes = new IDUMOFlowingData();
		pipes.add(str);
		return pipes;
	}

	@Override
	public boolean isReady() {
		return true;
	}

}
