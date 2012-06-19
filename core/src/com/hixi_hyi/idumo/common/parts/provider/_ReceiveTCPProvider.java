package com.hixi_hyi.idumo.common.parts.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.StringPrimitiveElement;
import com.hixi_hyi.idumo.core.exec.CoreController;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * バイト情報を受け取ることが出来るProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class _ReceiveTCPProvider implements Sendable, CoreController {
	private int port;

	private Socket socket;
	private BufferedReader br;
	private InputStream in;
	private ArrayList<String> strs;
	private AcceptServer server;

	public _ReceiveTCPProvider(int port) {
		this.port = port;
		strs = new ArrayList<String>();
		try {
			server = new AcceptServer(port);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isReady() {
		LogManager.log();

		if (server.getSocket() == null) {
			LogManager.debug("null");
			socket = null;
			if (!server.bool) {
				new Thread(server).start();
			}
			return false;
		}
		if (socket != server.getSocket()) {
			socket = server.getSocket();
			try {
				in = socket.getInputStream();
			}
			catch (IOException e) {
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
			}
			catch (IOException e) {
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
	public FlowingData onCall() {
		FlowingData p = new FlowingData();
		LogManager.debug(p);
		String s = strs.remove(0);
		p.add(new StringPrimitiveElement.StringPrimitiveData(s));
		server.restart();
		return p;
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
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ConnectDataType sendableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	class AcceptServer implements Runnable {

		int port;
		ServerSocket server;
		Socket socket;
		boolean bool;

		public AcceptServer(int port) throws IOException {
			this.port = port;
			server = new ServerSocket(port);
		}

		/**
		 * @return socket
		 */
		public Socket getSocket() {
			// if (socket.isClosed()) {
			// IDUMOLogManager.log();
			// new Thread(this).run();
			// socket = null;
			// }
			return socket;
		}

		public void restart() {
			try {
				socket.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			socket = null;
			new Thread(this).start();
		}

		@Override
		public void run() {
			try {
				bool = true;
				socket = server.accept();
				bool = false;
			}
			catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

	}
}
