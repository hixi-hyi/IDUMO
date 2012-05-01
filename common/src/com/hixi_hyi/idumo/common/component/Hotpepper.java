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
import org.jdom.Namespace;
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

	private ArrayList<HotpepperData>	list;
	private boolean				isReady;

	private URL2XMLParser parser;


	public Hotpepper(double lat,double lon) {
		init(lat,lon);
	}

	public Hotpepper(){
	}

	public void setLatLon(double lat,double lon){
		init(lat,lon);
	}

	public void init(double lat,double lon) {
		requestURL = String.format(REQUEST_URL_SEED, lat,lon);
		list = new ArrayList<HotpepperData>();
		try {
			parser = new URL2XMLParser(requestURL);
//			parser.output();
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
		Namespace ns = root.getNamespace();
		for (Element element : shops) {
			String name = element.getChildText("name",ns);
			String address = element.getChildText("address",ns);
			String catchcopy = element.getChildText("catch",ns);
			String open = element.getChildText("open",ns);
			String budget = element.getChild("budget",ns).getChildText("name", ns);
			String average = element.getChild("budget",ns).getChildText("average", ns);
			list.add(new HotpepperData(name, address, catchcopy, open, budget, average));
		}
	}

	public boolean isReady() {
//		if (isReady) {
//			return true;
//		}
//		init();
		return isReady;
	}

	public ArrayList<HotpepperData> getData() {
		return list;
	}

}
