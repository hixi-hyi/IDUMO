package com.hixi_hyi.idumo.console.receiptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.hixi_hyi.idumo.core.ApplicationController;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.ReceiverWithInputSize;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

public class SerialSendReceiptor implements IdumoRunnable, ReceiverWithInputSize, ApplicationController {
	
	private OutputStream	out;
	private Sender			sender;
	private String			serial;
	
	public SerialSendReceiptor(String serial) {
		this.serial = serial;
	}
	
	@Override
	public void run() {
		LogManager.log();
		if(!sender.isReady()){
			return ;
		}
		PipeData data = sender.getData();
		byte[] bytedata = new byte[data.size()];
		int i = 0;
		LogManager.debug("size: " + data.size());
		for (Object o : data) {
			LogManager.debug(o.toString());
			bytedata[i] = (Byte) o;
			i++;
		}
		
		try {
			out.write(bytedata);
			out.flush();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public boolean setSender(Sender... senders) throws IdumoException {
		if (senders.length != getInputSize()) {
			return false;
		}
		for (Object o : senders[0].getDataType()) {
			if (o != Byte.class) {
				return false;
			}
		}
		this.sender = senders[0];
		return true;
	}
	
	@Override
	public int getInputSize() {
		return 1;
	}
	
	@Override
	public void onIdumoStart() {
		try {
			out = new FileOutputStream(serial);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoStop() {
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
