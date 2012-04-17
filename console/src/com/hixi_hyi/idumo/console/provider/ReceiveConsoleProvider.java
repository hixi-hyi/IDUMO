package com.hixi_hyi.idumo.console.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public class ReceiveConsoleProvider implements IDUMOSender {

	private BufferedReader	br;

	public ReceiveConsoleProvider() {
		br = new BufferedReader(new InputStreamReader(System.in));

	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public List<Class<?>> getDataType() throws IDUMOException {
		List<Class<?>> type = new ArrayList<Class<?>>();
		type.add(String.class);
		return type;
	}

	@Override
	public IDUMOFlowingData get() {
		IDUMOFlowingData p = new IDUMOFlowingData();
		try {
			p.add(br.readLine());
		} catch (IOException e) {
			throw new IDUMORuntimeException(e);
		}
		return p;
	}

}
