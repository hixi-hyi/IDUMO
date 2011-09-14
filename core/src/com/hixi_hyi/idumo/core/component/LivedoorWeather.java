package com.hixi_hyi.idumo.core.component;

import java.net.URL;
import java.net.URLConnection;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 今日の天気予報を取得することが出来るクラス．
 * 
 * お天気Webサービス仕様 - Weather Hacks - livedoor 天気情報
 * http://weather.livedoor.com/weather_hacks/webservice.html API
 * http://weather.livedoor.com/forecast/webservice/rest/v1?city=63&day=today
 * Reference Site http://d.hatena.ne.jp/tomute/20080506/1210110326
 * 
 * @author Hiroyoshi
 * 
 */
public class LivedoorWeather {
	
	private static final String	REQUEST_URL_SEED	= "http://weather.livedoor.com/forecast/webservice/rest/v1?day=today&city=%d";
	
	private String				requestURL;
	
	private String				location;
	private String				date;
	private String				maxTemp;
	private String				minTemp;
	private String				weather;
	private String				description;
	
	private boolean				isReady;
	
	public LivedoorWeather(int citynum) {
		requestURL = String.format(REQUEST_URL_SEED, citynum);
		init();
	}
	
	public void init() {
		Document doc = null;
		try {
			URL accessURL = new URL(requestURL);
			URLConnection con = accessURL.openConnection();
			doc = new SAXBuilder().build(con.getInputStream());
			Element root = doc.getRootElement();
			date = root.getChildText("forecastdate");
			location = root.getChild("location").getAttributeValue("city");
			weather = root.getChildText("telop");
			description = root.getChildText("description");
			maxTemp = root.getChild("temperature").getChild("max").getChildText("celsius");
			minTemp = root.getChild("temperature").getChild("min").getChildText("celsius");
			
			isReady = true;
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * @return location
	 */
	public String getLocation() {
		return location;
	}
	
	public boolean isReady() {
		if (isReady) {
			return true;
		}
		init();
		return isReady;
	}
	
	/**
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @return maxTemp
	 */
	public String getMaxTemp() {
		return maxTemp;
	}
	
	/**
	 * @return minTemp
	 */
	public String getMinTemp() {
		return minTemp;
	}
	
	/**
	 * @return weather
	 */
	public String getWeather() {
		return weather;
	}
	
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
}
