package com.hixi_hyi.idumo.console.sample.seminer;

import com.hixi_hyi.idumo.console.exec.AbstractConsoleExecution;
import com.hixi_hyi.idumo.console.receiptor.SystemOutReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.IdumoRuntimeException;
import com.hixi_hyi.idumo.core.front.IdumoContainer;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.provider.LivedoorWeatherProvider;

public class TodayWeatherSeminer extends AbstractConsoleExecution {

	@Override
	public void onIdumoMakeFlowChart() throws IdumoException {
		LivedoorWeatherProvider weather = new LivedoorWeatherProvider(63);
		weather.setOption(LivedoorWeatherProvider.Type.WEATHER);
		add(weather);

		SystemOutReceiptor view = new SystemOutReceiptor();
		add(view);

		connect(weather, view);
	}

	@Override
	public void onIdumoPrepare() {
		setLoopCount(1);
		setSleepTime(1000);
	}

}

