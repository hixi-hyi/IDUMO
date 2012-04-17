package com.hixi_hyi.idumo.common.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOController;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
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
public class SendTCPReceiptor implements IDUMOReceiver, IDUMOController, IDUMORunnable {
	private String			ip;
	private int				port;
	private Socket			socket;
	private PrintWriter		pw;
	private OutputStream	outstream;
	private IDUMOSender			sender;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	private ReceiveValidator vType = new ReceiveValidatorType(1,String.class);

	public SendTCPReceiptor(String ip, int port) {
		IDUMOLogManager.log();
		this.ip = ip;
		this.port = port;
		socket = new Socket();
	}

	@Override
	public boolean isReady() {
		IDUMOLogManager.log();
		return socket.isConnected();
	}

	@Override
	public boolean setSender(IDUMOSender... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
		return true;
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
		IDUMOFlowingData data = sender.get();
		if(data==null){
			return ;
		}
		for (Object o : data) {
			IDUMOLogManager.debug(o.toString());
			pw.println(o.toString());
			pw.flush();
		}
	}

}
