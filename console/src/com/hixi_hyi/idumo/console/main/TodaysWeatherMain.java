package com.hixi_hyi.idumo.console.main;

import com.hixi_hyi.idumo.common.handler.StringConcatHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;

public class TodaysWeatherMain extends AbstractConsoleMain {

	@Override
	public void init() {
		setExecutionWithComponent(new TodaysWeatherComponent());
	}

	public class TodaysWeatherComponent extends IDUMOComponent {

		@Override
		public void onIdumoMakeFlowChart() throws IDUMOException {

			LivedoorWeatherProvider date = new LivedoorWeatherProvider(63);
			add(date);
			LivedoorWeatherProvider locate = new LivedoorWeatherProvider(63);
			add(locate);
			LivedoorWeatherProvider maxtemp = new LivedoorWeatherProvider(63);
			add(maxtemp);
			LivedoorWeatherProvider mintemp = new LivedoorWeatherProvider(63);
			add(mintemp);
			LivedoorWeatherProvider weather = new LivedoorWeatherProvider(63);
			add(weather);
			LivedoorWeatherProvider description = new LivedoorWeatherProvider(63);
			add(description);

			date.setOption(LivedoorWeatherProvider.Type.DATE);
			locate.setOption(LivedoorWeatherProvider.Type.LOCATION);
			maxtemp.setOption(LivedoorWeatherProvider.Type.MAX_TEMP);
			mintemp.setOption(LivedoorWeatherProvider.Type.MIN_TEMP);
			weather.setOption(LivedoorWeatherProvider.Type.WEATHER);
			description.setOption(LivedoorWeatherProvider.Type.DESCRIPTION);

			StringConcatHandler s1 = new StringConcatHandler("DATE:");
			add(s1);
			StringConcatHandler s2 = new StringConcatHandler("Location:");
			add(s2);
			StringConcatHandler s3 = new StringConcatHandler("Max:");
			add(s3);
			StringConcatHandler s4 = new StringConcatHandler("Min:");
			add(s4);
			StringConcatHandler s5 = new StringConcatHandler("Weather:");
			add(s5);
			StringConcatHandler s6 = new StringConcatHandler("Desc:");
			add(s6);

			ConsoleViewReceiptor console = new ConsoleViewReceiptor();
			add(console);

			connect(date, s1);
			connect(locate, s2);
			connect(maxtemp, s3);
			connect(mintemp, s4);
			connect(weather, s5);
			connect(description, s6);

			connect(s1, console);
			connect(s2, console);
			connect(s3, console);
			connect(s4, console);
			connect(s5, console);
			connect(s6, console);

		}

		@Override
		public void onIdumoPrepare() {
			setLoopCount(1);
			setSleepTime(1000);

		}
	}

	public static void main(String[] args) {
		TodaysWeatherMain main = new TodaysWeatherMain();
		main.exec();
	}

}
