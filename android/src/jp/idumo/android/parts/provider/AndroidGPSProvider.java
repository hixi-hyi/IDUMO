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
package jp.idumo.android.parts.provider;

import jp.idumo.android.annotation.IDUMOAndroid;
import jp.idumo.android.component.sensor.GPSSensor;
import jp.idumo.android.core.AndroidActivityController;
import jp.idumo.android.core.AndroidActivityResource;
import jp.idumo.android.core.AndroidController;
import jp.idumo.android.data.AndroidGPSData;
import jp.idumo.android.manifest.AndroidFeature;
import jp.idumo.android.manifest.AndroidPermission;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.annotation.IDUMOProvider;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.util.LogManager;
import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;

/**
 * GPS情報を取得できるProvider
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
@IDUMOAndroid(permissions = { AndroidPermission.ACCESS_FINE_LOCATION, AndroidPermission.ACCESS_MOCK_LOCATION, AndroidPermission.ACCESS_COARSE_LOCATION }, features = { AndroidFeature.LOCATION, AndroidFeature.LOCATION_GPS, AndroidFeature.LOCATION_NETWORK })
@IDUMOProvider(send = AndroidGPSData.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "GPSセンサ", summary = "AndroidのGPSセンサ")
public class AndroidGPSProvider implements Sendable, AndroidController, AndroidActivityController {
	
	private GPSSensor	gps;
	
	public AndroidGPSProvider() {
		gps = GPSSensor.INSTANCE;
		// lazy initialize (method of registActivity)
	}
	
	@Override
	public boolean isReady() {
		return gps.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		LogManager.log();
		FlowingData p = new FlowingData();
		p.add(new AndroidGPSData(gps.getLatitude(), gps.getLongitude(), gps.getAltitude(), gps.getTime(), gps.getBearing(), gps.getSpeed()));
		return p;
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		gps.unregister();
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		gps.register();
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(AndroidGPSData.class);
	}
	
	@Override
	public void registActivity(AndroidActivityResource activity) {
		if (!gps.isInit()) {
			LocationManager location = (LocationManager) activity.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
			gps.init(location);
		}
	}
	
}
