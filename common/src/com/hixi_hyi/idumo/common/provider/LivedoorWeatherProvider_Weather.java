package com.hixi_hyi.idumo.common.provider;

import java.util.ArrayList;
import java.util.List;

import com.hixi_hyi.idumo.common.component.LivedoorWeather;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * LivedoorWeatherからデータを取得し，提供するプロバイダ
 * 
 * @author Hiroyoshi
 * 
 */
public class LivedoorWeatherProvider_Weather implements IDUMOSender {
	
	private LivedoorWeather	weather;
	
	public LivedoorWeatherProvider_Weather(int citynum) {
		weather = new LivedoorWeather(citynum);
	}
	
	@Override
	public List<Class<?>> getDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		types.add(String.class);
		return types;
	}
	
	@Override
	public IDUMOFlowingData getData() {
		IDUMOLogManager.log();
		System.out.println(weather.getWeather());
		return IDUMOFlowingData.generatePipeData(weather.getWeather());
		
	}
	
	@Override
	public boolean isReady() {
		return weather.isReady();
	}
}
