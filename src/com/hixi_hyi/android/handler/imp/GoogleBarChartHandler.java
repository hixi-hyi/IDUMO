package com.hixi_hyi.android.handler.imp;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.event.IdumoEvent;
import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.eventlistener.HandlerListener;
import com.hixi_hyi.android.handler.DataHandler;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.provider.DataProviderInterface;

public class GoogleBarChartHandler extends DataHandler {

	private final String BASE_URL = "http://chart.apis.google.com/chart?chxt=x,y&chxl=0:|1|2|3|4|5|6|7|8|9|10|1:|0|10&chs=400x400&cht=lc&chco=ff0000&chd=t:";

	@Override
	public void setParameter(PipeData parameter) {
	}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		// TODO パラメータの追加
		return false;
	}

	@Override
	public void providerChanged(ProviderEvent e) {
		localDataChange(e);
	}

	@Override
	public void handlerChanged(HandlerEvent e) {
		localDataChange(e);
	}

	public void localDataChange(IdumoEvent e) {
		StringBuilder sb = new StringBuilder();

		for (Object o : e.getPipeData()) {
			// Log.v(this.getClass().getSimpleName(), ((Integer)o).toString());
			Float i = (Float) o;
			sb.append(Math.abs(i) * 5);
			sb.append(",");
		}
		// Log.v(this.getClass().getSimpleName(),sb.toString());
		sb.deleteCharAt(sb.length() - 1);
		Bitmap bm;
		try {
			URL url = new URL(BASE_URL + sb.toString());
			InputStream is = url.openStream();
			bm = BitmapFactory.decodeStream(is);
			dataChange(new HandlerEvent(new PipeData(bm)));
		} catch (Exception exception) {
			Log.v("test", exception.toString());
		}
	}

	@Override
	public boolean isRegist(DataProviderInterface providerInterface) {
		for(Class<?> c:providerInterface.getNotifyDataType()){
			if(c!=Integer.class){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isRegist(DataHandlerInterface handlerInterface) {
		for(Class<?> c:handlerInterface.getNotifyDataType()){
			if(c!=Integer.class){
				return false;
			}
		}
		return true;
	}
	@Override
	public Collection<Class<?>> getNotifyDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		types.add(Bitmap.class);
		return types;
	}



}
