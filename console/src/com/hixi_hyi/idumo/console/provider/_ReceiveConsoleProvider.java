package com.hixi_hyi.idumo.console.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveString;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

public class _ReceiveConsoleProvider implements IDUMOSendable {

	private BufferedReader br;

	public _ReceiveConsoleProvider() {
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
			p.add(new IDUMODataPrimitiveString(br.readLine()));
		} catch (IOException e) {
			throw new IDUMORuntimeException(e);
		}
		return p;
	}

	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveString.class);
	}

}
