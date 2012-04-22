package com.hixi_hyi.idumo.common.handler.raw;

import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

public class IDUMOGetValueHandler implements IDUMOSendable, IDUMOReceivable {

	@Override
	public boolean isReady() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean setSender(IDUMOSendable... senders) throws IDUMOException {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public IDUMODataConnect receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public IDUMODataFlowing onCall() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public IDUMODataConnect sendableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
