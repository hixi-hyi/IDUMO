package com.hixi_hyi.idumo.console.receiptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOController;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class SerialSendReceiptor implements IDUMORunnable, IDUMOReceivable,
		IDUMOController {

	private OutputStream out;
	private IDUMOSendable sender;
	private String serial;

	public SerialSendReceiptor(String serial) {
		this.serial = serial;
	}

	@Override
	public void run() {
		IDUMOLogManager.log();
		if (!sender.isReady()) {
			return;
		}
		IDUMODataFlowing data = sender.onCall();
		if (data == null) {
			return;
		}
		byte[] bytedata = new byte[data.size()];
		int i = 0;
		IDUMOLogManager.debug("size: " + data.size());
		for (Object o : data) {
			IDUMOLogManager.debug(o.toString());
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
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
//		if (senders.length != 1) {
//			return false;
//		}
		// for (Object o : senders[0].getDataType()) {
		// if (o != Byte.class) {
		// return false;
		// }
		// }
		this.sender = senders[0];
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

	@Override
	public IDUMODataConnect receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
