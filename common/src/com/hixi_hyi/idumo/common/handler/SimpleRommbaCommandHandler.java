package com.hixi_hyi.idumo.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.common.component.ConvertRoombaCommand;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.ReceiverWithOption;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class SimpleRommbaCommandHandler implements Sender, ReceiverWithOption {

	private Sender	sender;

	public SimpleRommbaCommandHandler() {}

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
		LogManager.log();
		String command = (String) sender.getData().get(0);
		PipeData p = new PipeData();
		if (false) {

		} else if (command.equals("SAFE")) {
			p.add(ConvertRoombaCommand.getCommand(command));
		} else if (command.equals("CLEAN")) {
			p.add(ConvertRoombaCommand.getCommand(command));
		} else if (command.equals("SPOT")) {
			p.add(ConvertRoombaCommand.getCommand(command));
		} else if (command.equals("STOP")) {
			p.add(ConvertRoombaCommand.getCommand("DRIVE_DIRECT"));
			p.add(new Byte((byte) 0x00));
			p.add(new Byte((byte) 0x00));
			p.add(new Byte((byte) 0x00));
			p.add(new Byte((byte) 0x00));
		} else if (command.equals("FORWARD")) {
			p.add(ConvertRoombaCommand.getCommand("DRIVE_DIRECT"));
			p.add(new Byte((byte) 0x00));
			p.add(new Byte((byte) 0x20));
			p.add(new Byte((byte) 0x00));
			p.add(new Byte((byte) 0x20));
		} else if (command.equals("FORWARD60")) {
			p.add(ConvertRoombaCommand.getCommand("DRIVE_DIRECT"));
			p.add(new Byte((byte) 0x01));
			p.add(new Byte((byte) 0xF4));
			p.add(new Byte((byte) 0x01));
			p.add(new Byte((byte) 0xF4));
		} else if (command.equals("BACK")) {
			p.add(ConvertRoombaCommand.getCommand("DRIVE_DIRECT"));
			p.add(new Byte((byte) 0xFF));
			p.add(new Byte((byte) 0xEC));
			p.add(new Byte((byte) 0xFF));
			p.add(new Byte((byte) 0xEC));
		} else if (command.equals("BACK60")) {
			p.add(ConvertRoombaCommand.getCommand("DRIVE_DIRECT"));
			p.add(new Byte((byte) 0xFF));
			p.add(new Byte((byte) 0xC6));
			p.add(new Byte((byte) 0xFF));
			p.add(new Byte((byte) 0xC6));
		} else if (command.equals("LEFT")) {
			p.add(ConvertRoombaCommand.getCommand("DRIVE_DIRECT"));
			p.add(new Byte((byte) 0x00));
			p.add(new Byte((byte) 0x20));
			p.add(new Byte((byte) 0xFF));
			p.add(new Byte((byte) 0xEC));
		} else if (command.equals("RIGHT")) {
			p.add(ConvertRoombaCommand.getCommand("DRIVE_DIRECT"));
			p.add(new Byte((byte) 0xFF));
			p.add(new Byte((byte) 0xEC));
			p.add(new Byte((byte) 0x00));
			p.add(new Byte((byte) 0x20));
		} else if (command.equals("RECORD")) {
			p.add(ConvertRoombaCommand.getCommand("SONG"));
			byte[] song = { 0, 12, 72, 16, 74, 16, 76, 48, 74, 16, 72, 16, 30, 16, 72, 16, 74, 16, 76, 16, 74, 16, 72, 16, 74, 64 };
			for (byte b : song) {
				p.add(b);
			}
		} else if (command.equals("SING")) {
			p.add(ConvertRoombaCommand.getCommand("PLAY"));
			p.add((byte) 0);
		} else if (command.equals("DOCK")) {
			p.add(ConvertRoombaCommand.getCommand("SEEK_DOCK"));
		}
		return p;
	}

}
