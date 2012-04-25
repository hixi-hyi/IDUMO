package com.hixi_hyi.idumo.common.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveString;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOController;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * バイト情報をTCP通信を用いて送ることが出来るReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class SendTCPReceiptor implements IDUMOReceivable, IDUMOController, IDUMORunnable {
	private String				ip;
	private int					port;
	private Socket				socket;
	private PrintWriter			pw;
	private OutputStream		outstream;
	private IDUMOSendable		sender;
	private ReceiveValidator	vSize	= new ReceiveValidatorSize(1);
	private ReceiveValidator	vType	= new ReceiveValidatorType(1, IDUMODataPrimitiveString.class);
	
	public SendTCPReceiptor(String ip, int port) {
		IDUMOLogManager.log();
		this.ip = ip;
		this.port = port;
		socket = new Socket();
	}
	
	@Override
	public boolean isReady() {
		IDUMOLogManager.log();
		return sender.isReady() && socket.isConnected();
	}
	
	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
	}
	
	@Override
	public void onIdumoStart() {
		IDUMOLogManager.log();
		try {
			socket.connect(new InetSocketAddress(ip, port));
			outstream = socket.getOutputStream();
			pw = new PrintWriter(new OutputStreamWriter(outstream));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoStop() {
		try {
			socket.close();
			outstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		for (Object o : data) {
			IDUMOLogManager.debug(o.toString());
			pw.println(o.toString());
			pw.flush();
		}
	}
	
	@Override
	public IDUMODataTypeConnect receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
