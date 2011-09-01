package com.hixi_hyi.idumo.console.sample;

import com.hixi_hyi.idumo.console.receiptor.SystemOutReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.provider.LivedoorWeatherProvider;

public class Weather2Console {

	public static void main(String[] args){
		try {
			LivedoorWeatherProvider date = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider locate = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider maxtemp = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider mintemp = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider weather = new LivedoorWeatherProvider(63);
			LivedoorWeatherProvider description = new LivedoorWeatherProvider(63);

			date.setOption(LivedoorWeatherProvider.Type.DATE);
			locate.setOption(LivedoorWeatherProvider.Type.LOCATION);
			maxtemp.setOption(LivedoorWeatherProvider.Type.MAX_TEMP);
			mintemp.setOption(LivedoorWeatherProvider.Type.MIN_TEMP);
			weather.setOption(LivedoorWeatherProvider.Type.WEATHER);
			description.setOption(LivedoorWeatherProvider.Type.DESCRIPTION);

			StringConcatHandler s1 = new StringConcatHandler("DATE:");
			StringConcatHandler s2 = new StringConcatHandler("Location:");
			StringConcatHandler s3 = new StringConcatHandler("Max:");
			StringConcatHandler s4 = new StringConcatHandler("Min:");
			StringConcatHandler s5 = new StringConcatHandler("Weather:");
			StringConcatHandler s6 = new StringConcatHandler("Desc:");

			s1.setSender(date);
			s2.setSender(locate);
			s3.setSender(maxtemp);
			s4.setSender(mintemp);
			s5.setSender(weather);
			s6.setSender(description);

			SystemOutReceiptor console= new SystemOutReceiptor();

			console.setSender(s1,s2,s3,s4,s5,s6);

			console.run();

		} catch (IdumoException e) {
			e.printStackTrace();
		}

	}
}
