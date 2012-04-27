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
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.hixi_hyi.idumo.common.data.HotpepperData;
import com.hixi_hyi.idumo.common.data.LivedoorWeatherData;
import com.hixi_hyi.idumo.common.util.URL2XMLParser;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

public class Hotpepper {
	private static final String API_KEY = "bdcd74ea41fe6b8d";
	private static final String	REQUEST_URL_SEED	= "http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key="+API_KEY+"&lat=%f&lng=%f";

	private String				requestURL;

	private String				location;
	private String				date;
	private double				maxTemp;
	private double				minTemp;
	private String				weather;
	private String				description;

	private HotpepperData	data;
	private boolean				isReady;
	
	private URL2XMLParser parser;
	

	public Hotpepper(double lat,double lon) {
		requestURL = String.format(REQUEST_URL_SEED, lat,lon);
		init();
	}

	public void init() {
		try {
			parser = new URL2XMLParser(requestURL);
			parser.output();
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
		Element root = parser.getRoot();
		List<Element> shops = new ArrayList<Element>();
		List<Element> children = (List<Element>)root.getChildren();
		for (Element element : children) {
			String name = element.getName();
			if(name.equals("shop")){
				shops.add(element);
			}
		}
		for (Element element : shops) {
			System.out.println(element);
			System.out.println(element.getChildren("name"));
			String name;
			name = element.getChildText("shop");
			System.out.println(name);
		}
//		System.out.println( root.getChildren() );
		
		
		/*
		date = root.getChildText("forecastdate");
		location = root.getChild("location").getAttributeValue("city");
		weather = root.getChildText("telop");
		description = root.getChildText("description");
		try {
			maxTemp = Double.parseDouble(root.getChild("temperature").getChild("max").getChildText("celsius"));
		} catch (Exception e) {}
		try {
			minTemp = Double.parseDouble(root.getChild("temperature").getChild("min").getChildText("celsius"));
		} catch (Exception e) {}
		data = new LivedoorWeatherData(location, date, maxTemp, minTemp, weather, description);
		isReady = true;
		*/
	}

	public boolean isReady() {
		if (isReady) {
			return true;
		}
		init();
		return isReady;
	}

	public HotpepperData getData() {
		return data;
	}

}
