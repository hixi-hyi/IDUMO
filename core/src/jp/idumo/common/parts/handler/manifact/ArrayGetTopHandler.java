package jp.idumo.common.parts.handler.manifact;

import jp.idumo.core.annotation.IDUMOHandler;
import jp.idumo.core.data.DataElement;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.ThroughElement;
import jp.idumo.core.data.connect.ArrayConnectDataType;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.validator.ReceiveValidatorSize;

@IDUMOHandler(author = "Hiroyoshi HOUCHI", name = "配列の一番上を取得", description = "配列の一番上の値を取得する", receive = DataElement.class, send = ThroughElement.class)
public class ArrayGetTopHandler implements Sendable, Receivable {

	private Sendable				sender;
	private ReceiveValidatorSize	vSize	= new ReceiveValidatorSize(1);

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public FlowingData onCall() {
		DataElement d = sender.onCall().next();
		return new FlowingData(d);
	}

	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(DataElement.class);
	}

	@Override
	public ConnectDataType sendableType() {
		return sender.sendableType();
	}

	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}

}
