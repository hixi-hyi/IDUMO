package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class StringConcatHandler_Suffix implements IDUMOSendable, IDUMOReceivable {

	private IDUMOSendable provider;
	private String fixWord;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);

	public StringConcatHandler_Suffix(String fixWord) {
		this.fixWord = fixWord;
	}

	@Override
	public IDUMODataFlowing onCall() {
		// LogUtil.d();
		StringBuilder sb = new StringBuilder();
		for (Object o : provider.onCall()) {
			sb.append(o.toString());
		}
		sb.append(fixWord);
		IDUMODataFlowing p = new IDUMODataFlowing();
		p.add(new IDUMOStringData(sb.toString()));
		return p;
	}

	@Override
	public boolean setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		this.provider = senders[0];
		return true;
	}

	@Override
	public boolean isReady() {
		return (provider != null) && provider.isReady();
	}

	@Override
	public IDUMODataConnect receivableType() {
		return new IDUMODataConnectSingle(IDUMOData.class);
	}

	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOStringData.class);
	}

}
