package com.hixi_hyi.idumo.console.sample;

import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.console.exec.ConsoleExecution;

public class TodaysWeatherMain extends AbstractConsoleMain {

	@Override
	public void init() {
		TodaysWeatherComponent component = new TodaysWeatherComponent();
		ConsoleExecution execution = new ConsoleExecution(component);
		setExecution(execution);
	}

	public static void main(String[] args) {
		TodaysWeatherMain main = new TodaysWeatherMain();
		main.exec();
	}

}
