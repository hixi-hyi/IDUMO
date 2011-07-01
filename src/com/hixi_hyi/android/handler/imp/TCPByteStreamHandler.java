package com.hixi_hyi.android.handler.imp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;

import android.util.Log;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.event.IdumoEvent;
import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.eventlistener.HandlerListener;
import com.hixi_hyi.android.handler.DataHandler;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.provider.DataProviderInterface;

public class TCPByteStreamHandler extends DataHandler {
	protected String ip;
	protected int port;
	protected Socket sock;
	protected OutputStream outstream;

	public TCPByteStreamHandler(String ip,int port){
		this.ip = ip;
		this.port = port;
	}

	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.handler.DataHandler#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		try {
			sock = new Socket(ip,port);
			outstream = sock.getOutputStream();
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.handler.DataHandler#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
		try {
			sock.close();
			outstream.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	@Override
	public void setParameter(PipeData parameter) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void providerChanged(ProviderEvent e) {
		changed(e);
	}

	@Override
	public void handlerChanged(HandlerEvent e) {
		changed(e);
	}

	public void changed(IdumoEvent e){
		PipeData data =e.getPipeData();
		byte[] bytedata = new byte[data.size()];
		int i=0;
		for(Object o:data){
			bytedata[i] = (Byte)o;
			i++;
		}

		Log.v(this.getClass().toString(),"data:"+(Byte)data.get(0));
		try{
			outstream.write(bytedata);
		}
		catch(Exception exception){
			System.out.println(exception);
			return;
		}
	}

	@Override
	public boolean isRegist(DataProviderInterface providerInterface) {
		for(Class<?> c:providerInterface.getNotifyDataType()){
			if(c!=Byte.class){
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean isRegist(DataHandlerInterface handlerInterface) {
		for(Class<?> c:handlerInterface.getNotifyDataType()){
			if(c!=Byte.class){
				return false;
			}
		}
		return true;
	}


	@Override
	public Collection<Class<?>> getNotifyDataType() {
		// TODO これどうしよう…終点なんだけど…
		return null;
	}




}
