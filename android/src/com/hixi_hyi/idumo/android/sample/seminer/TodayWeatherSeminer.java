package com.hixi_hyi.idumo.android.sample.seminer;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecution;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.component.LivedoorWeather;
import com.hixi_hyi.idumo.core.provider.LivedoorWeatherProvider;

public class TodayWeatherSeminer extends AbstractAndroidExecution {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		LivedoorWeatherProvider weather = new LivedoorWeatherProvider(63);
		weather.setOption(LivedoorWeatherProvider.Type.WEATHER);
		add(weather);

		TextViewReceiptor view = new TextViewReceiptor(this);
		add(view);

		connect(weather, view);

	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(-1);
		setSleepTime(1000);
	}

}
