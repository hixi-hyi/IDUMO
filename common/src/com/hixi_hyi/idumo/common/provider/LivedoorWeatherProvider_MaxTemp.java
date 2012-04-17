package com.hixi_hyi.idumo.common.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hixi_hyi.idumo.common.component.LivedoorWeather;
import com.hixi_hyi.idumo.core.OptionMethodType;
import com.hixi_hyi.idumo.core.SenderWithOption;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * LivedoorWeatherからデータを取得し，提供するプロバイダ
 * 
 * @author Hiroyoshi
 * 
 */
public class LivedoorWeatherProvider_MaxTemp implements SenderWithOption {
	
	public enum Type implements OptionMethodType {
		LOCATION(""), DATE(""), MAX_TEMP(""), MIN_TEMP(""), WEATHER(""), DESCRIPTION(""), ;
		private final String	description;
		
		Type(String description) {
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}
	}
	
	private Type			type;
	private LivedoorWeather	weather;
	
	public LivedoorWeatherProvider_MaxTemp(int citynum) {
		weather = new LivedoorWeather(citynum);
	}
	
	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		types.add(String.class);
		return types;
	}
	
	@Override
	public IDUMOFlowingData get() {
		IDUMOLogManager.log();
		IDUMOFlowingData p = new IDUMOFlowingData();
		switch (type) {
			case LOCATION:
				p.add(weather.getLocation());
				break;
			case DATE:
				p.add(weather.getDate());
				break;
			case MAX_TEMP:
				p.add(weather.getMaxTemp());
				break;
			case MIN_TEMP:
				p.add(weather.getMinTemp());
				break;
			case WEATHER:
				p.add(weather.getWeather());
				break;
			case DESCRIPTION:
				p.add(weather.getDescription());
				break;
			default:
				throw new IDUMORuntimeException();
		}
		
		return p;
		
	}
	
	@Override
	public boolean isReady() {
		return weather.isReady();
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
	public void setOption(OptionMethodType type) throws IDUMOException {
		if (type instanceof Type) {
			this.type = (Type) type;
		} else {
			throw new IDUMOException();
		}
	}
	
}
