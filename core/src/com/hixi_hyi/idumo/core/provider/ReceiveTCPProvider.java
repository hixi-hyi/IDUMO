package com.hixi_hyi.idumo.core.provider;

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
import com.hixi_hyi.idumo.core.ReceiverWithOption;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * バイト情報を受け取ることが出来るProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class ReceiveTCPProvider implements ReceiverWithOption, ApplicationController, IdumoRunnable {
	private String			ip;
	private int				port;
	private Socket			socket;
	private PrintWriter		pw;
	private OutputStream	outstream;
	private Sender			sender;

	public ReceiveTCPProvider(String ip, int port) {
		this.ip = ip;
		this.port = port;
		socket = new Socket();
	}

	@Override
	public int getInputSize() {
		return 1;
	}

	@Override
	public boolean isReady() {
		return socket.isConnected();
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
	public void onIdumoStart() {
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
		PipeData data = sender.getData();
		for (Object o : data) {
			LogManager.debug(o.toString());
			pw.println(o.toString());
		}
	}

}
