package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.common.component.ConvertRoombaCommand;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.ReceiverWithInputSize;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;

public class ConvertRommbaCommandHandler implements Sender, ReceiverWithInputSize {

	private Sender	sender;

	public ConvertRommbaCommandHandler() {}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public boolean setSender(Sender... senders) throws IdumoException {
		if (senders.length != getInputSize()) {
			throw new IdumoException();
		}
		List<Class<?>> type = senders[0].getDataType();
		if ((type.size() != 1) && (type.get(0) != String.class)) {
			throw new IdumoException();
		}
		this.sender = senders[0];
		return true;
	}

	@Override
	public int getInputSize() {
		return 1;
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
