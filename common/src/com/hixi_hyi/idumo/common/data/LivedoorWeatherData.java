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
package com.hixi_hyi.idumo.common.data;

import com.hixi_hyi.idumo.core.data.Data.AbstractData;
import com.hixi_hyi.idumo.core.data.raw.NumberRawDataType;
import com.hixi_hyi.idumo.core.data.raw.StringRawDataType;

/**
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
public class LivedoorWeatherData extends AbstractData {
	public static final String	LOCATION	= "location";
	public static final String	DATE		= "date";
	public static final String	MAX_TEMP	= "max_temp";
	public static final String	MIN_TEMP	= "min_temp";
	public static final String	WEATHER		= "weather";
	public static final String	DESCRIPTION	= "description";
	
	public LivedoorWeatherData(String location, String date, Double maxTemp, Double minTemp, String weather, String description) {
		add(new StringRawDataType(LOCATION, location, "livedoor location"));
		add(new StringRawDataType(DATE, date, "livedoor date"));
		add(new NumberRawDataType(MAX_TEMP, maxTemp, "livedoor max temp"));
		add(new NumberRawDataType(MIN_TEMP, minTemp, "livedoor min temp"));
		add(new StringRawDataType(WEATHER, weather, "livedoor weather"));
		add(new StringRawDataType(DESCRIPTION, description, "livedoor description"));
	}
	
	public String getLocation() {
		return (String) getValue(LOCATION);
	}
	
	public String getDate() {
		return (String) getValue(DATE);
	}
	
	public String getMaxTemp() {
		return (String) getValue(MAX_TEMP);
	}
	
	public String getMinTemp() {
		return (String) getValue(MIN_TEMP);
	}
	
	public String getWeather() {
		return (String) getValue(WEATHER);
	}
	
	public String getDescription() {
		return (String) getValue(DESCRIPTION);
	}
}
