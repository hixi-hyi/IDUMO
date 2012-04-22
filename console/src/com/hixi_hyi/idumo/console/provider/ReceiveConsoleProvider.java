package com.hixi_hyi.idumo.console.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

public class ReceiveConsoleProvider implements IDUMOSendable {

	private BufferedReader br;

	public ReceiveConsoleProvider() {
		br = new BufferedReader(new InputStreamReader(System.in));

	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMODataFlowing p = new IDUMODataFlowing();
		try {
			p.add(new IDUMOStringData(br.readLine()));
		} catch (IOException e) {
			throw new IDUMORuntimeException(e);
		}
		return p;
	}

	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOStringData.class);
	}

}
