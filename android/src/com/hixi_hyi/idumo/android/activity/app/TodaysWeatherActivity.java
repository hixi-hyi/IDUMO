package com.hixi_hyi.idumo.android.activity.app;

import com.hixi_hyi.idumo.android.exec.AbstractAndroidExecutionComponent;
import com.hixi_hyi.idumo.android.exec.AbstractAndroidActivity;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.handler.StringConcatHandler;
import com.hixi_hyi.idumo.core.provider.LivedoorWeatherProvider;

public class TodaysWeatherActivity extends AbstractAndroidActivity {

	@Override
	public void init() {
		setExecutionWithComponent(new TodaysWeatherComponent());
	}

	public class TodaysWeatherComponent extends AbstractAndroidExecutionComponent {

		@Override
		public void onIdumoMakeFlowChart() throws IdumoException {
			LivedoorWeatherProvider date = new LivedoorWeatherProvider(63);
			date.setOption(LivedoorWeatherProvider.Type.DATE);
			add(date);

			LivedoorWeatherProvider locate = new LivedoorWeatherProvider(63);
			locate.setOption(LivedoorWeatherProvider.Type.LOCATION);
			add(locate);

			LivedoorWeatherProvider maxtemp = new LivedoorWeatherProvider(63);
			maxtemp.setOption(LivedoorWeatherProvider.Type.MAX_TEMP);
			add(maxtemp);

			LivedoorWeatherProvider mintemp = new LivedoorWeatherProvider(63);
			mintemp.setOption(LivedoorWeatherProvider.Type.MIN_TEMP);
			add(mintemp);

			LivedoorWeatherProvider weather = new LivedoorWeatherProvider(63);
			weather.setOption(LivedoorWeatherProvider.Type.WEATHER);
			add(weather);

			LivedoorWeatherProvider description = new LivedoorWeatherProvider(63);
			description.setOption(LivedoorWeatherProvider.Type.DESCRIPTION);
			add(description);

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

			TextViewReceiptor textView = new TextViewReceiptor(activity);
			add(textView);

			connect(date, s1);
			connect(locate, s2);
			connect(maxtemp, s3);
			connect(mintemp, s4);
			connect(weather, s5);
			connect(description, s6);

			connect(s1, textView);
			connect(s2, textView);
			connect(s3, textView);
			connect(s4, textView);
			connect(s5, textView);
			connect(s6, textView);

		}

		@Override
		public void onIdumoPrepare() {
			setLoopCount(-1);
			setSleepTime(1000);
		}

	}

}
