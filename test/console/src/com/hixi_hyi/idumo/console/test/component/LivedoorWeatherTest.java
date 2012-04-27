package com.hixi_hyi.idumo.console.test.component;

import java.io.IOException;

import org.jdom.JDOMException;

import com.hixi_hyi.idumo.common.component.Hotpepper;
import com.hixi_hyi.idumo.common.component.LivedoorWeather;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;

public class LivedoorWeatherTest {
	public static void main(String args[]) throws IOException, JDOMException{
		LivedoorWeather weather = new LivedoorWeather(63);
		System.out.println(weather.getData());

	}

}
