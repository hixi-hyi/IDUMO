package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.common.component.ConvertRoombaCommand;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

public class ConvertRommbaCommandHandler implements IDUMOSender, IDUMOReceiver {

	private IDUMOSender	sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1,String.class); 

	public ConvertRommbaCommandHandler() {}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
		return true;
	}

	@Override
	public List<Class<?>> getDataType() throws IDUMOException {
		List<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Byte.class);
		return type;
	}

	@Override
	public IDUMOFlowingData get() {
		String command = (String) sender.get().get(0);
		IDUMOFlowingData p = new IDUMOFlowingData();
		if(ConvertRoombaCommand.containsKey(command)){
			p.add(ConvertRoombaCommand.getCommand(command));
		}
		return p;
	}

}
