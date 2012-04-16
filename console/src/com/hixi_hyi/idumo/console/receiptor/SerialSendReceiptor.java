package com.hixi_hyi.idumo.console.receiptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.hixi_hyi.idumo.core.IDUMOController;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.LogManager;

public class SerialSendReceiptor implements IDUMORunnable, IDUMOReceiver, IDUMOController {

	private OutputStream	out;
	private IDUMOSender			sender;
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
		if(data==null){
			return;
		}
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
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		if (senders.length != 1) {
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
