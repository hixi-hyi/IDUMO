package com.hixi_hyi.idumo.common.provider;

import com.hixi_hyi.idumo.common.component.LivedoorWeather;
import com.hixi_hyi.idumo.common.data.IDUMOLivedoorWeatherData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMOFlowingData;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * LivedoorWeatherからデータを取得し，提供するプロバイダ
 * 
 * @author Hiroyoshi
 * 
 */
public class LivedoorWeatherProvider implements IDUMOSender {

	private LivedoorWeather weather;

	public LivedoorWeatherProvider(int citynum) {
		weather = new LivedoorWeather(citynum);
	}

	@Override
	public IDUMOFlowingData onCall() {
		IDUMOLogManager.log();
		IDUMOFlowingData p = new IDUMOFlowingData();
		p.add(weather.getData());
		return p;
	}

	@Override
	public boolean isReady() {
		return weather.isReady();
	}

	@Override
	public Class<? extends IDUMOData> sendableType() {
		return IDUMOLivedoorWeatherData.class;
	}

}
