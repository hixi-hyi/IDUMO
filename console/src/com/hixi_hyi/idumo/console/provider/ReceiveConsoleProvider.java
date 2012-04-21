package com.hixi_hyi.idumo.console.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;

public class ReceiveConsoleProvider implements IDUMOSender {

	private BufferedReader br;

	public ReceiveConsoleProvider() {
		br = new BufferedReader(new InputStreamReader(System.in));

	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public IDUMOFlowingData onCall() {
		IDUMOFlowingData p = new IDUMOFlowingData();
		try {
			p.add(new IDUMOStringData(br.readLine()));
		} catch (IOException e) {
			throw new IDUMORuntimeException(e);
		}
		return p;
	}

	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMOStringData.class;
	}

}
