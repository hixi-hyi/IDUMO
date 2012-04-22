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

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRawNumber;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRawString;

public class IDUMOLivedoorWeatherData extends IDUMOData {
	public static final String LOCATION = "location";
	public static final String DATE = "date";
	public static final String MAX_TEMP = "max_temp";
	public static final String MIN_TEMP = "min_temp";
	public static final String WEATHER = "weather";
	public static final String DESCRIPTION = "weather";

	public IDUMOLivedoorWeatherData(String location, String date,
			Double maxTemp, Double minTemp, String weather, String description) {
		add(new IDUMODataTypeRawString(LOCATION, location, ""));
		add(new IDUMODataTypeRawString(DATE, date, ""));
		add(new IDUMODataTypeRawNumber(MAX_TEMP, maxTemp, ""));
		add(new IDUMODataTypeRawNumber(MIN_TEMP, minTemp, ""));
		add(new IDUMODataTypeRawString(WEATHER, weather, ""));
		add(new IDUMODataTypeRawString(DESCRIPTION, description, ""));
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
