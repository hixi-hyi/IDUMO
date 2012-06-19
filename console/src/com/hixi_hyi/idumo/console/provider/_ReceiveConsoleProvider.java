package com.hixi_hyi.idumo.console.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.StringPrimitiveElement;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.parts.Sendable;

public class _ReceiveConsoleProvider implements Sendable {

	private BufferedReader	br;

	public _ReceiveConsoleProvider() {
		br = new BufferedReader(new InputStreamReader(System.in));

	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public FlowingData onCall() {
		FlowingData p = new FlowingData();
		try {
			p.add(new StringPrimitiveElement.StringPrimitiveData(br.readLine()));
		} catch (IOException e) {
			throw new IDUMORuntimeException(e);
		}
		return p;
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(StringPrimitiveElement.class);
	}

}
