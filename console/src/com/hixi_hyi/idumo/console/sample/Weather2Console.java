package com.hixi_hyi.idumo.console.sample;

import com.hixi_hyi.idumo.console.receiptor.SystemOutReceiptor;
import com.hixi_hyi.idumo.console.util.ConsoleLogger;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.front.IdumoContainer;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.core.util.LogManager;

public class Weather2Console {

	public static void main(String[] args){
		try {
			LogManager.DEBUG = true;
			LogManager.LOGGER = new ConsoleLogger();

			IdumoContainer container = new IdumoContainer();

			LivedoorWeatherProvider date = new LivedoorWeatherProvider(63);
			container.add(date);
			LivedoorWeatherProvider locate = new LivedoorWeatherProvider(63);
			container.add(locate);
			LivedoorWeatherProvider maxtemp = new LivedoorWeatherProvider(63);
			container.add(maxtemp);
			LivedoorWeatherProvider mintemp = new LivedoorWeatherProvider(63);
			container.add(mintemp);
			LivedoorWeatherProvider weather = new LivedoorWeatherProvider(63);
			container.add(weather);
			LivedoorWeatherProvider description = new LivedoorWeatherProvider(63);
			container.add(description);

			date.setOption(LivedoorWeatherProvider.Type.DATE);
			locate.setOption(LivedoorWeatherProvider.Type.LOCATION);
			maxtemp.setOption(LivedoorWeatherProvider.Type.MAX_TEMP);
			mintemp.setOption(LivedoorWeatherProvider.Type.MIN_TEMP);
			weather.setOption(LivedoorWeatherProvider.Type.WEATHER);
			description.setOption(LivedoorWeatherProvider.Type.DESCRIPTION);

			StringConcatHandler s1 = new StringConcatHandler("DATE:");
			container.add(s1);
			StringConcatHandler s2 = new StringConcatHandler("Location:");
			container.add(s2);
			StringConcatHandler s3 = new StringConcatHandler("Max:");
			container.add(s3);
			StringConcatHandler s4 = new StringConcatHandler("Min:");
			container.add(s4);
			StringConcatHandler s5 = new StringConcatHandler("Weather:");
			container.add(s5);
			StringConcatHandler s6 = new StringConcatHandler("Desc:");
			container.add(s6);

			SystemOutReceiptor console= new SystemOutReceiptor();
			container.add(console);

			container.connect(date, s1);
			container.connect(locate, s2);
			container.connect(maxtemp, s3);
			container.connect(mintemp, s4);
			container.connect(weather, s5);
			container.connect(description, s6);

			container.connect(s1, console);
			container.connect(s2, console);
			container.connect(s3, console);
			container.connect(s4, console);
			container.connect(s5, console);
			container.connect(s6, console);

			container.compile();

			for(IdumoRunnable r: container.getRunnables()){
				r.run();
			}


		} catch (IdumoException e) {
			e.printStackTrace();
		}

	}
}
