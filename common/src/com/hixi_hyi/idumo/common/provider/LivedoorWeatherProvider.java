package com.hixi_hyi.idumo.common.provider;

import com.hixi_hyi.idumo.common.component.LivedoorWeather;
import com.hixi_hyi.idumo.common.data.IDUMOLivedoorWeatherData;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataConnectSingle;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * LivedoorWeatherからデータを取得し，提供するプロバイダ
 * 
 * @author Hiroyoshi
 * 
 */
public class LivedoorWeatherProvider implements IDUMOSendable {

	private LivedoorWeather weather;

	public LivedoorWeatherProvider(int citynum) {
		weather = new LivedoorWeather(citynum);
	}

	@Override
	public IDUMODataFlowing onCall() {
		IDUMODataFlowing p = new IDUMODataFlowing();
		p.add(weather.getData());
		return p;
	}

	@Override
	public boolean isReady() {
		return weather.isReady();
	}

	@Override
	public IDUMODataConnect sendableType() {
		return new IDUMODataConnectSingle(IDUMOLivedoorWeatherData.class);
	}

}
