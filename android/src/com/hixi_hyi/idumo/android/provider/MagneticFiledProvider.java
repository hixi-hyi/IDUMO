package com.hixi_hyi.idumo.android.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.activity.sensor.MagneticActivity;
import com.hixi_hyi.idumo.android.sensor.MagneticFieldSensor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上の地磁気センサの情報を取得できるProvider
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class MagneticFiledProvider implements SenderWithOption,AndroidController {

	public enum Type implements OptionMethodType {
		X("Get MagneticFiled X"), Y("Get MagneticFiled Y"), Z("Get MagneticFiled Z");
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
	private MagneticFieldSensor	magnet;

	public MagneticFiledProvider(Activity activity) {
		SensorManager sensor = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		MagneticFieldSensor magneticFieldSensor = MagneticFieldSensor.INSTANCE;
		magneticFieldSensor.init(sensor);
		this.magnet = magneticFieldSensor;
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
				p.add(magnet.getX());
				break;
			case Y:
				p.add(magnet.getY());
				break;
			case Z:
				p.add(magnet.getZ());
				break;
			default:
				throw new IdumoRuntimeException();
		}
		return p;
	}

	@Override
	public void setOption(OptionMethodType type) throws IdumoException {
		if (type instanceof Type) {
			methodType = (Type) type;
		} else {
			throw new IdumoException();
		}
	}

	@Override
	public boolean isReady() {
		return magnet.isReady();
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
	public void onIdumoStart() {
	}

	@Override
	public void onIdumoStop() {
	}

	@Override
	public void onIdumoRestart() {
	}

	@Override
	public void onIdumoResume() {
		magnet.register();
	}

	@Override
	public void onIdumoPause() {
		magnet.unregister();
	}

	@Override
	public void onIdumoDestroy() {
	}

}
