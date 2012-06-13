/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.hixi_hyi.idumo.common.component;

import java.io.IOException;

import org.jdom.Element;
import org.jdom.JDOMException;

import com.hixi_hyi.idumo.common.data.LivedoorWeatherData;
import com.hixi_hyi.idumo.common.util.URL2XMLParser;

/**
 * 今日の天気予報を取得することが出来るクラス．
 * 
 * お天気Webサービス仕様 - Weather Hacks - livedoor 天気情報
 * http://weather.livedoor.com/weather_hacks/webservice.html API
 * http://weather.livedoor.com/forecast/webservice/rest/v1?city=63&day=today
 * Reference Site http://d.hatena.ne.jp/tomute/20080506/1210110326
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
public class LivedoorWeather {
	
	private static final String REQUEST_URL_SEED = "http://weather.livedoor.com/forecast/webservice/rest/v1?day=today&city=%d";
	
	private String requestURL;
	
	private LivedoorWeatherData data;
	
	private boolean isReady;
	private URL2XMLParser parser;
	
	public LivedoorWeather(int citynum) throws IOException, JDOMException {
		requestURL = String.format(REQUEST_URL_SEED, citynum);
		init();
	}
	
	public LivedoorWeatherData getData() {
		return data;
	}
	
	public void init() throws IOException, JDOMException {
		parser = new URL2XMLParser(requestURL);
		Element root = parser.getRoot();
		// parser.output();
		String date = root.getChildText("forecastdate");
		String location = root.getChild("location").getAttributeValue("city");
		String weather = root.getChildText("telop");
		String description = root.getChildText("description");
		double maxTemp = 0.0, minTemp = 0.0;
		try {
			maxTemp = Double.parseDouble(root.getChild("temperature").getChild("max").getChildText("celsius"));
		}
		catch (Exception e) {}
		try {
			minTemp = Double.parseDouble(root.getChild("temperature").getChild("min").getChildText("celsius"));
		}
		catch (Exception e) {}
		data = new LivedoorWeatherData(location, date, maxTemp, minTemp, weather, description);
		isReady = true;
	}
	
	public boolean isReady() throws IOException, JDOMException {
		if (isReady) {
			return true;
		}
		init();
		return isReady;
	}
	
}
