package com.hixi_hyi.idumo.console.sample;

import com.hixi_hyi.idumo.console.util.ConsoleLogger;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Weather2ConsoleMain {
	public static void main(String[] args) {
		// LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		
		Weather2Console weather = new Weather2Console();
		try {
			weather.onIdumoBuild();
			weather.onIdumoStart();
			weather.onIdumoExec();
			weather.onIdumoStop();
		} catch (IdumoException e) {
			e.printStackTrace();
		}
	}
}
