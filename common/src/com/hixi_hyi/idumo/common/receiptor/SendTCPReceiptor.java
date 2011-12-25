package com.hixi_hyi.idumo.common.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.hixi_hyi.idumo.core.ApplicationController;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.ReceiverWithInputSize;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * バイト情報をTCP通信を用いて送ることが出来るReceiptor
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class SendTCPReceiptor implements Receiver, ApplicationController, IdumoRunnable {
	private String			ip;
	private int				port;
	private Socket			socket;
	private PrintWriter		pw;
	private OutputStream	outstream;
	private Sender			sender;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	private ReceiveValidator vType = new ReceiveValidatorType(1,String.class);
	
	public SendTCPReceiptor(String ip, int port) {
		LogManager.log();
		this.ip = ip;
		this.port = port;
		socket = new Socket();
	}

	@Override
	public boolean isReady() {
		LogManager.log();
		return socket.isConnected();
	}

	@Override
	public boolean setSender(Sender... senders) throws IdumoException {
		vSize.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
		return true;
	}

	@Override
	public void onIdumoStart() {
		LogManager.log();
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
		LogManager.log();
		if (!sender.isReady()) {
			return;
		}
		PipeData data = sender.getData();
		for (Object o : data) {
			LogManager.debug(o.toString());
			pw.println(o.toString());
			pw.flush();
		}
	}

}
