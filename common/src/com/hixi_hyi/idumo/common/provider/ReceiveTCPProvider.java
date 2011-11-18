package com.hixi_hyi.idumo.common.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.StartDocument;

import sun.awt.windows.ThemeReader;

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
public class ReceiveTCPProvider implements Sender, ApplicationController {
	private int					port;
	private Socket				socket;
	private BufferedReader		br;
	private InputStream			in;
	private ArrayList<String>	strs;
	private AcceptServer		server;

	public ReceiveTCPProvider(int port) {
		this.port = port;
		this.strs = new ArrayList<String>();
		try {
			this.server = new AcceptServer(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isReady() {
		LogManager.log();

		if (server.getSocket() == null) {
			LogManager.debug("null");
			socket = null;
			if(!server.bool){
				new Thread(server).start();
			}
			return false;
		}
		if (socket != server.getSocket()) {
			socket = server.getSocket();
			try {
				in = socket.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			br = new BufferedReader(new InputStreamReader(in));
		}

		if (strs.size() != 0) {
			return true;
		} else {
			String s;
			try {
				if ((s = br.readLine()) != null) {
					strs.add(s);
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;

		/*
		 * if (server.getSocket() == null) { return false; } socket =
		 * server.getSocket(); try { in = socket.getInputStream(); } catch
		 * (IOException e) { e.printStackTrace(); } br = new BufferedReader(new
		 * InputStreamReader(in)); String s; try { if ((s = br.readLine()) !=
		 * null) { strs.add(s); } server.socket = null; return true; } catch
		 * (IOException e) { e.printStackTrace(); } return false;
		 */
	}

	@Override
	public void onIdumoStart() {
		new Thread(server).run();
	}

	@Override
	public void onIdumoStop() {
		try {
			socket.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Class<?>> getDataType() throws IdumoException {
		List<Class<?>> type = new ArrayList<Class<?>>();
		type.add(String.class);
		return type;
	}

	@Override
	public PipeData getData() {
		PipeData p = new PipeData();
		String s = strs.remove(0);
		p.add(s);
		server.restart();
		return p;
	}

	class AcceptServer implements Runnable {

		int				port;
		ServerSocket	server;
		Socket			socket;
		boolean         bool;
		public AcceptServer(int port) throws IOException {
			this.port = port;
			server = new ServerSocket(port);
		}

		@Override
		public void run() {
			try {
				bool = true;
				socket = server.accept();
				bool = false;
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		/**
		 * @return socket
		 */
		public Socket getSocket() {
			// if (socket.isClosed()) {
			// LogManager.log();
			// new Thread(this).run();
			// socket = null;
			// }
			return socket;
		}

		public void restart() {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			socket = null;
			new Thread(this).start();
		}

	}
}
