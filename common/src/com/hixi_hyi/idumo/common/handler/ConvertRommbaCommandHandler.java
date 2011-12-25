package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.common.component.ConvertRoombaCommand;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.ReceiverWithInputSize;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ConvertRommbaCommandHandler implements Sender, Receiver {

	private Sender	sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1,String.class); 

	public ConvertRommbaCommandHandler() {}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public boolean setSender(Sender... senders) throws IdumoException {
		vSize.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
		return true;
	}

	@Override
	public List<Class<?>> getDataType() throws IdumoException {
		List<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Byte.class);
		return type;
	}

	@Override
	public PipeData getData() {
		String command = (String) sender.getData().get(0);
		PipeData p = new PipeData();
		if(ConvertRoombaCommand.containsKey(command)){
			p.add(ConvertRoombaCommand.getCommand(command));
		}
		return p;
	}

}
