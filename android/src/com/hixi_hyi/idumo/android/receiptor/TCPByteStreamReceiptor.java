package com.hixi_hyi.idumo.android.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * バイト情報をTCP通信を用いて送ることが出来るReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class TCPByteStreamReceiptor implements IDUMOReceivable, AndroidController, IDUMORunnable {
	private String			ip;
	private int				port;
	private Socket			socket;
	private OutputStream	outstream;
	private IDUMOSendable		sender;
	
	public TCPByteStreamReceiptor(String ip, int port) {
		this.ip = ip;
		this.port = port;
		socket = new Socket();
	}
	
	@Override
	public boolean isReady() {
		return socket.isConnected();
	}
	
	@Override
	public boolean setSender(IDUMOSendable... senders) throws IDUMOException {
		if (senders.length != 1) {
			return false;
		}
		// for (Object o : senders[0].getDataType()) {
		// if (o != Byte.class) {
		// return false;
		// }
		// }
		this.sender = senders[0];
		return true;
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		try {
			socket.connect(new InetSocketAddress(ip, port));
			outstream = socket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoPause() {
		try {
			socket.close();
			outstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void run() {
		IDUMOLogManager.log();
		IDUMODataFlowing data = sender.onCall();
		byte[] bytedata = new byte[data.size()];
		int i = 0;
		IDUMOLogManager.debug("size: " + data.size());
		for (Object o : data) {
			IDUMOLogManager.debug(o.toString());
			bytedata[i] = (Byte) o;
			i++;
		}
		
		try {
			outstream.write(bytedata);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	@Override
	public IDUMODataConnect receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
