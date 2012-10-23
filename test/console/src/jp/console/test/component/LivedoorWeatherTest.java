package jp.console.test.component;

import java.io.IOException;

import jp.idumo.common.component.LivedoorWeather;

import org.jdom.JDOMException;

public class LivedoorWeatherTest {
	public static void main(String args[]) throws IOException, JDOMException {
		LivedoorWeather weather = new LivedoorWeather(63);
		System.out.println(weather.getData());
		
	}
	
}
