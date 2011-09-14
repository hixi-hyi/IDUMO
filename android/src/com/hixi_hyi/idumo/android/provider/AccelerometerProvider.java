package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hixi_hyi.idumo.android.sensor.AccelerometerSensor;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上の加速度センサの値を提供するProvider
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class AccelerometerProvider implements SenderWithOption {
	
	public enum Type implements OptionMethodType {
		X("Get Accelerometer X"), Y("Get Accelerometer Y"), Z("Get Accelerometer Z");
		private final String	description;
		
		Type(String description) {
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}
	}
	
	private Type				methodType;
	private AccelerometerSensor	accel;
	
	public AccelerometerProvider(AccelerometerSensor accel) {
		this.accel = accel;
	}
	
	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Float.class);
		return type;
	}
	
	@Override
	public PipeData getData() {
		LogManager.log();
		PipeData p = new PipeData();
		switch (methodType) {
			case X:
				p.add(accel.getX());
				break;
			case Y:
				p.add(accel.getY());
				break;
			case Z:
				p.add(accel.getZ());
				break;
			default:
				throw new IdumoRuntimeException();
		}
		return p;
	}
	
	@Override
	public void setOption(OptionMethodType type) {
		if (type instanceof Type) {
			methodType = (Type) type;
		} else {
			throw new IdumoRuntimeException();
		}
	}
	
	@Override
	public boolean isReady() {
		return accel.isReady();
	}
	
	@Override
	public Map<String, String> getOptions() {
		Map<String, String> map = new HashMap<String, String>();
		for (OptionMethodType t : Type.values()) {
			map.put(t.toString(), t.getDescription());
		}
		return map;
	}
	
}
