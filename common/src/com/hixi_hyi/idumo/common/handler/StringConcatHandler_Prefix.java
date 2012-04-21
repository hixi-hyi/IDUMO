package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.common.data.IDUMOStringData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

public class StringConcatHandler_Prefix implements IDUMOSender, IDUMOReceiver {

	private IDUMOSender provider;
	private String fixWord;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);

	public StringConcatHandler_Prefix(String fixWord) {
		this.fixWord = fixWord;
	}

	@Override
	public IDUMOFlowingData onCall() {
		// LogUtil.d();
		StringBuilder sb = new StringBuilder();
		sb.append(fixWord);
		for (Object o : provider.onCall()) {
			sb.append(o.toString());
		}
		IDUMOFlowingData p = new IDUMOFlowingData();
		p.add(new IDUMOStringData(sb.toString()));
		return p;
	}

	@Override
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		vSize.validate(senders);
		this.provider = senders[0];
		return true;
	}

	@Override
	public boolean isReady() {
		return (provider != null) && provider.isReady();
	}

	@Override
	public Class<? extends IDUMOData> receivableType() {
		return IDUMOData.class;
	}

	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMOStringData.class;
	}

}
