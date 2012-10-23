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

import jp.idumo.common.data.element.LatLngElement;
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
import android.content.Context;

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

@IDUMOReceiptor(author="Hiroyoshi HOUCHI",name="地図の表示",receive=LatLngElement.class)

public class AndroidMapViewReceiptor extends MapView implements Receivable, Executable {
	
	private Sendable				sender;
	private Activity				activity;
	private ReceiveValidatorSize	vSize		= new ReceiveValidatorSize(1);
	
	private static final int		ZOOM_LEVEL	= 10;
	private MapController			controller;
	
	private static final String		API_KEY		= "0RPjiLm_GLRAHM0HCn22WyqMNKfeWGuSvvXnqoA";
	
	public AndroidMapViewReceiptor(Context context) {
		super(context, API_KEY);
		activity = (Activity) context;
		activity.setContentView(this);
		
		controller = getController();
		controller.setZoom(ZOOM_LEVEL);
		
		setClickable(true);
		setBuiltInZoomControls(true);
		setSatellite(false);
		
	}
	
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
		invalidate();
	}
	
	@Override
	public void setSender(Sendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}
	
}
