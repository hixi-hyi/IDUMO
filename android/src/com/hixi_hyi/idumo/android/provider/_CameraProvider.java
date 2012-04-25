package com.hixi_hyi.idumo.android.provider;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;

import com.hixi_hyi.idumo.android.sensor._CameraSensor;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

public class _CameraProvider implements SenderWithOption {
	
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
	private _CameraSensor	camera;
	private Activity		activity;
	
	public _CameraProvider(Context context) {
		activity = (Activity) context;
		camera = new _CameraSensor(context);
		methodType = Type.TOUCH;
	}
	
	@Override
	public IDUMODataFlowing onCall() {
		activity.setContentView(camera);
		camera.takePicture();
		return null;
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
	public void setOption(OptionMethodType type) throws IDUMOException {
		if (type instanceof Type) {
			methodType = (Type) type;
		} else {
			throw new IDUMOException();
		}
	}
	
	@Override
	public IDUMODataTypeConnect sendableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
