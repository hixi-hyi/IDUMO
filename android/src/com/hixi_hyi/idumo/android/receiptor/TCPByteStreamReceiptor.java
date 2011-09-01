package com.hixi_hyi.idumo.android.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.util.LogUtil;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.ReceiverWithOption;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;

/**
 * バイト情報をTCP通信を用いて送ることが出来るReceiptor
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class TCPByteStreamReceiptor implements ReceiverWithOption,ApplicationControlforAndroid,IdumoRunnable {
	private String ip;
	private int port;
	private Socket socket;
	private OutputStream outstream;
	private Sender sender;

	public TCPByteStreamReceiptor(String ip,int port){
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
		if(senders.length!=getInputSize()){
			return false;
		}
		for(Object o:senders[0].getDataType()){
			if(o!=Byte.class){
				return false;
			}
		}
		this.sender = senders[0];
		return true;
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onRestart() {
	}

	@Override
	public void onResume() {
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
	public void onPause() {
		try {
			socket.close();
			outstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStop() {
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public void run() {
		LogUtil.d();
		PipeData data =sender.getData();
		byte[] bytedata = new byte[data.size()];
		int i=0;
		LogUtil.d("size: "+data.size());
		for(Object o:data){
			LogUtil.d(o.toString());
			bytedata[i] = (Byte)o;
			i++;
		}

		try{
			outstream.write(bytedata);
		}catch(Exception exception){
			exception.printStackTrace();
		}


	}

}
