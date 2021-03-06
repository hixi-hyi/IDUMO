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
package jp.idumo.android.data;

import jp.idumo.common.data.element.LatLngElement;
import jp.idumo.core.data.DataElement.AbstractData;
import jp.idumo.core.data.raw.NumberRawDataType;

/**
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 */
public class AndroidGPSData extends AbstractData implements LatLngElement {
	public static final String	LATITUDE	= "latitude";
	public static final String	LONGITUDE	= "longitude";
	public static final String	ALTITUDE	= "altitude";
	public static final String	TIME		= "time";
	public static final String	BEARING		= "bearing";
	public static final String	SPEED		= "speed";
	
	public AndroidGPSData(double latitude, double longitude, double altitude, long time, float bearing, float speed) {
		add(new NumberRawDataType(LATITUDE, latitude, "Android GPS latitude"));
		add(new NumberRawDataType(LONGITUDE, longitude, "Android GPS Longitude"));
		add(new NumberRawDataType(ALTITUDE, altitude, "Android GPS Altitude"));
		add(new NumberRawDataType(TIME, time, "Android GPS Time"));
		add(new NumberRawDataType(BEARING, bearing, "Android GPS Bearing"));
		add(new NumberRawDataType(SPEED, speed, "Android GPS Speed"));
	}
	
	public double getAltitude() {
		return (Double) getValue(ALTITUDE);
	}
	
	public float getBearing() {
		return (Float) getValue(BEARING);
	}
	
	@Override
	public double getLatitude() {
		return (Double) getValue(LATITUDE);
	}
	
	@Override
	public double getLongitude() {
		return (Double) getValue(LONGITUDE);
	}
	
	public float getSpeed() {
		return (Float) getValue(SPEED);
	}
	
	public long getTime() {
		return (Long) getValue(TIME);
	}
	
	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder();
		sb.append(LATITUDE);
		sb.append(":");
		sb.append(getLatitude());
		sb.append("\n");
		sb.append(LONGITUDE);
		sb.append(":");
		sb.append(getLongitude());
		sb.append("\n");
		sb.append(ALTITUDE);
		sb.append(":");
		sb.append(getAltitude());
		sb.append("\n");
		sb.append(TIME);
		sb.append(":");
		sb.append(getTime());
		sb.append("\n");
		sb.append(BEARING);
		sb.append(":");
		sb.append(getBearing());
		sb.append("\n");
		sb.append(SPEED);
		sb.append(":");
		sb.append(getSpeed());
		sb.append("\n");
		return sb.toString();
	}
}
