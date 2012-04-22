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
package com.hixi_hyi.idumo.android.data;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRawNumber;

public class IDUMOAndroidGPSData extends IDUMOData {
	public static final String	LATITUDE	= "latitude";
	public static final String	LONGITUDE	= "longitude";
	public static final String	ALTITUDE	= "altitude";
	public static final String	TIME		= "time";
	public static final String	BEARING		= "bearing";
	public static final String	SPEED		= "speed";
	
	public IDUMOAndroidGPSData(double latitude, double longitude, double altitude, long time, float bearing, float speed) {
		add(new IDUMODataTypeRawNumber(LATITUDE, latitude, "Android GPS latitude"));
		add(new IDUMODataTypeRawNumber(LONGITUDE, longitude, "Android GPS"));
		add(new IDUMODataTypeRawNumber(ALTITUDE, altitude, "Android GPS"));
		add(new IDUMODataTypeRawNumber(TIME, time, "Android GPS"));
		add(new IDUMODataTypeRawNumber(BEARING, bearing, "Android GPS"));
		add(new IDUMODataTypeRawNumber(SPEED, speed, "Android GPS"));
	}
	
	public double getLatitude() {
		return (Double) getValue(LATITUDE);
	}
	
	public double getLongitude() {
		return (Double) getValue(LONGITUDE);
	}
	
	public double getAltitude() {
		return (Double) getValue(ALTITUDE);
	}
	
	public long getTime() {
		return (Long) getValue(TIME);
	}
	
	public float getBearing() {
		return (Float) getValue(BEARING);
	}
	
	public float getSpeed() {
		return (Float) getValue(SPEED);
	}
}
