package com.hixi_hyi.idumo.common.component;

import java.net.URL;
import java.net.URLConnection;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.hixi_hyi.idumo.common.data.IDUMOLivedoorWeatherData;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;

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

	private static final String REQUEST_URL_SEED = "http://weather.livedoor.com/forecast/webservice/rest/v1?day=today&city=%d";

	private String requestURL;

	private String location;
	private String date;
	private double maxTemp;
	private double minTemp;
	private String weather;
	private String description;

	private IDUMOLivedoorWeatherData data;

	private boolean isReady;

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
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}

		Element root = doc.getRootElement();
		date = root.getChildText("forecastdate");
		location = root.getChild("location").getAttributeValue("city");
		weather = root.getChildText("telop");
		description = root.getChildText("description");
		try {
			maxTemp = Double.parseDouble(root.getChild("temperature")
					.getChild("max").getChildText("celsius"));
		} catch (Exception e) {
		}
		try {
			minTemp = Double.parseDouble(root.getChild("temperature")
					.getChild("min").getChildText("celsius"));
		} catch (Exception e) {
		}
		data = new IDUMOLivedoorWeatherData(location, date, maxTemp, minTemp,
				weather, description);
		isReady = true;
	}

	public boolean isReady() {
		if (isReady) {
			return true;
		}
		init();
		return isReady;
	}

	public IDUMOLivedoorWeatherData getData() {
		return data;
	}

}
