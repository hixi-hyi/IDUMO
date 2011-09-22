package com.hixi_hyi.idumo.console.sample.seminer;

import com.hixi_hyi.idumo.console.util.ConsoleLogger;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.util.LogManager;

public class TodayWeatherSeminerMain {
	public static void main(String[] args) {
		// LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();

		TodayWeatherSeminer weather = new TodayWeatherSeminer();
		try {
			weather.onIdumoMakeFlowChart();
			weather.onIdumoStart();
			weather.onIdumoPrepare();
			weather.onIdumoExec();
			weather.onIdumoStop();
		} catch (IdumoException e) {
			e.printStackTrace();
		}
	}
}
