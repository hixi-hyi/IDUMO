package com.hixi_hyi.idumo.console.sample;

import com.hixi_hyi.idumo.console.exec.ConsoleExecution;
import com.hixi_hyi.idumo.console.util.ConsoleLogger;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.sun.tools.internal.xjc.model.CPluginCustomization;

public class TodayWeatherMain {
	public static void main(String[] args) {
		// LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();

		ConsoleExecution execution = new ConsoleExecution(new TodayWeather());
		try {
			execution.onIdumoCreated();
			execution.onIdumoStart();
			execution.onIdumoExec();
			execution.onIdumoStop();
		} catch (IdumoException e) {
			e.printStackTrace();
		}
	}
}
