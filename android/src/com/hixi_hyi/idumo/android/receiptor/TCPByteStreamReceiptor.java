package com.hixi_hyi.idumo.android.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.hixi_hyi.idumo.android.core.AndroidController;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * バイト情報をTCP通信を用いて送ることが出来るReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class TCPByteStreamReceiptor implements IDUMOReceiver, AndroidController, IDUMORunnable {
	private String			ip;
	private int				port;
	private Socket			socket;
	private OutputStream	outstream;
	private IDUMOSender		sender;
	
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
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
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
		IDUMOFlowingData data = sender.onCall();
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
	public Class<? extends IDUMOData> receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
