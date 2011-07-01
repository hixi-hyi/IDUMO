package com.hixi_hyi.android.handler.imp;

import java.io.IOException;
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
import com.hixi_hyi.android.handler.DataHandler;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.provider.DataProviderInterface;

public class TCPStringStreamHandler extends TCPByteStreamHandler {

	private PrintWriter pw;

	public TCPStringStreamHandler(String ip, int port) {
		super(ip,port);
	}


	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.handler.DataHandler#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		pw = new PrintWriter(new OutputStreamWriter(outstream));
	}


	/* (非 Javadoc)
	 * @see com.hixi_hyi.android.handler.DataHandler#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
		pw.close();
	}


	@Override
	public void setParameter(PipeData parameter) {
	}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
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
		String outputstr = data.get(0).toString();
		Log.v(this.getClass().getSimpleName(),"data:"+outputstr);
		try{
			pw.println(outputstr);
			pw.flush();
		}
		catch(Exception exception){
			exception.printStackTrace();
			return;
		}
	}

	@Override
	public boolean isRegist(DataProviderInterface providerInterface) {
		if(providerInterface.getNotifyDataType().size()==1){
			return true;
		}
		return true;
	}


	@Override
	public boolean isRegist(DataHandlerInterface handlerInterface) {
		if(handlerInterface.getNotifyDataType().size()==1){
			return true;
		}
		return true;
	}


	@Override
	public Collection<Class<?>> getNotifyDataType() {
		// TODO これどうしよう…終点なんだけど…
		return null;
	}




}
