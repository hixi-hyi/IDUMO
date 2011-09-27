package com.hixi_hyi.idumo.console.sample;

import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;

public class TodaysWeatherMain extends AbstractConsoleMain {

	@Override
	public void init() {
		setExecution(new TodaysWeatherComponent());
//		TodaysWeatherComponent component = new TodaysWeatherComponent();
//		ConsoleExecution execution = new ConsoleExecution(component);
//		setExecution(execution);
	}

	public static void main(String[] args) {
		TodaysWeatherMain main = new TodaysWeatherMain();
		main.exec();
	}

}
