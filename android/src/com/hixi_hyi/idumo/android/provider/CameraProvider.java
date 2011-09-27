package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.hixi_hyi.idumo.android.sensor.CameraSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.PipeData;

public class CameraProvider implements SenderWithOption {
	
	public enum Type implements OptionMethodType {
		TOUCH("");
		private final String	description;
		
		Type(String description) {
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}
	}
	
	private Type			methodType;
	private CameraSensor	camera;
	private Activity		activity;
	
	public CameraProvider(Context context) {
		activity = (Activity) context;
		camera = new CameraSensor(context);
		methodType = Type.TOUCH;
	}
	
	@Override
	public PipeData getData() {
		activity.setContentView(camera);
		camera.takePicture();
		return null;
	}
	
	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> type = new ArrayList<Class<?>>();
		type.add(Bitmap.class);
		return type;
	}
	
	@Override
	public Map<String, String> getOptions() {
		Map<String, String> map = new HashMap<String, String>();
		for (OptionMethodType t : Type.values()) {
			map.put(t.toString(), t.getDescription());
		}
		return map;
	}
	
	@Override
	public boolean isReady() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	@Override
	public void setOption(OptionMethodType type) throws IdumoException {
		if (type instanceof Type) {
			methodType = (Type) type;
		} else {
			throw new IdumoException();
		}
	}
	
}
