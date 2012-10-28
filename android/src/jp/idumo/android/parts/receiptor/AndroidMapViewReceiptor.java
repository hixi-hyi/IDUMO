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
package jp.idumo.android.parts.receiptor;

import jp.idumo.android.annotation.IDUMOAndroid;
import jp.idumo.android.core.AndroidActivityController;
import jp.idumo.android.manifest.AndroidLibrary;
import jp.idumo.android.manifest.AndroidPermission;
import jp.idumo.common.data.element.LatLngElement;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.annotation.IDUMOReceiptor;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Executable;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.util.LogManager;
import jp.idumo.core.validator.ReceiveValidatorSize;
import android.app.Activity;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Android上にテキスト情報を出力するReceiptorです
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
@IDUMOAndroid(libraries = { AndroidLibrary.MAPS }, permissions = { AndroidPermission.INTERNET })
@IDUMOReceiptor(receive = LatLngElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "地図の表示", summary = "GoogleMap")
public class AndroidMapViewReceiptor implements Receivable, Executable, AndroidActivityController {
	
	private MapView					view;
	private Sendable				sender;
	private Activity				activity;
	private ReceiveValidatorSize	vSize		= new ReceiveValidatorSize(1);
	
	private static final int		ZOOM_LEVEL	= 10;
	private MapController			controller;
	
	private static final String		API_KEY		= "0A1Cx9Pq6v1LrPccIpXJpStEaqtgxeo-1qC6zJw";
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(LatLngElement.class);
	}
	
	@Override
	public void run() {
		LogManager.log();
		FlowingData idf = sender.onCall();
		LatLngElement llde = (LatLngElement) idf.next();
		GeoPoint point = new GeoPoint((int) (llde.getLatitude() * 1E6), (int) (llde.getLongitude() * 1E6));
		LogManager.debug(point);
		controller.setCenter(point);
		view.invalidate();
	}
	
	@Override
	public void setSender(Sendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}
	
	@Override
	public void registActivity(Activity activity) {
		view = new MapView(activity, API_KEY);
		activity.setContentView(view);
		
		controller = view.getController();
		controller.setZoom(ZOOM_LEVEL);
		
		view.setClickable(true);
		view.setBuiltInZoomControls(true);
		view.setSatellite(false);
	}
	
}
