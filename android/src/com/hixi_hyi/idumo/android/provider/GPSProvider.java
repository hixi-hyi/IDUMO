package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hixi_hyi.idumo.android.sensor.GPSSensor;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * GPS情報を取得できるProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class GPSProvider implements SenderWithOption{

	public enum Type implements OptionMethodType{
		ACCURARY(""),
		ALTITUDE(""),
		BEARING(""),
		LATITUDE(""),
		LONGITUDE(""),
		SPEED(""),
		TIME("")
		;
		private final String description;
		Type(String description){
			this.description = description;
		}
		@Override
		public String getDescription() {
			return description;
		}
	}


	private GPSSensor gps;
	private Type methodType;


	public GPSProvider(GPSSensor gpsSensor){
		this.gps = gpsSensor;
	}

	@Override
	public List<Class<?>> getDataType() throws IdumoException {
		List<Class<?>> type = new ArrayList<Class<?>>();
		switch (methodType) {
		case ACCURARY:
			type.add(Float.class);
			break;
		case ALTITUDE:
			type.add(Double.class);
			break;
		case BEARING:
			type.add(Float.class);
			break;
		case LATITUDE:
			type.add(Double.class);
			break;
		case LONGITUDE:
			type.add(Double.class);
			break;
		case SPEED:
			type.add(Float.class);
			break;
		case TIME:
			type.add(Long.class);
			break;
		default:
			throw new IdumoException();
		}
		return type;
	}

	@Override
	public PipeData getData() {
		LogManager.log();
		if(!isReady()){
			return null;
		}

		PipeData p = new PipeData();
		switch (methodType) {
		case ACCURARY:
			p.add(gps.getAccuracy());
			break;
		case ALTITUDE:
			p.add(gps.getAltitude());
			break;
		case BEARING:
			p.add(gps.getBearing());
			break;
		case LATITUDE:
			p.add(gps.getLatitude());
			break;
		case LONGITUDE:
			p.add(gps.getLongitude());
			break;
		case SPEED:
			p.add(gps.getSpeed());
			break;
		case TIME:
			p.add(gps.getTime());
			break;
		default:
			break;
		}
		return p;
	}

	@Override
	public boolean isReady() {
		return gps.isReady();
	}

	@Override
	public Map<String, String> getOptions() {
		Map<String, String> map = new HashMap<String, String>();
		for(OptionMethodType t: Type.values()){
			map.put(t.toString(), t.getDescription());
		}
		return map;
	}

	@Override
	public void setOption(OptionMethodType type) throws IdumoException{
		if(type instanceof Type){
			methodType = (Type)type;
		}else{
			throw new IdumoException();
		}
	}

}
