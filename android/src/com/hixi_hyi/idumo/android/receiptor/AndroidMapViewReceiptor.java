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
package com.hixi_hyi.idumo.android.receiptor;


import java.util.ArrayList;
import java.util.List;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.hixi_hyi.idumo.common.data.element.LatLngDataElement;
import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

/**
 * Android上にテキスト情報を出力するReceiptorです
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
public class AndroidMapViewReceiptor extends MapView implements IDUMOReceivable, IDUMORunnable {
	
	private IDUMOSendable	sender;
	private Activity					activity;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	
	private static final int ZOOM_LEVEL = 10;
	private MapController controller;
	
	private static final String API_KEY = "0RPjiLm_GLRAHM0HCn22WyqMNKfeWGuSvvXnqoA";
	
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
	public void run() {
		IDUMOLogManager.log();
		IDUMODataFlowing idf = sender.onCall();
		LatLngDataElement llde = (LatLngDataElement)idf.next();
		GeoPoint point = new GeoPoint((int)(llde.getLatitude()*1E6), (int)(llde.getLongitude()*1E6));	
		IDUMOLogManager.debug(point);
		controller.setCenter(point);	
		invalidate();
	}
	
	@Override
	public void setSender(IDUMOSendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public IDUMODataTypeConnect receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
class PinItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private List<OverlayItem> items = new ArrayList<OverlayItem>();
	public PinItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	@Override
	protected OverlayItem createItem(int i) {
		return items.get(i);
	}
	@Override
	public int size() {
		return items.size();
	}
	public void addPin(GeoPoint point, String title, String snippet) {
		items.add(new OverlayItem(point, title, snippet));
		populate();
	}
}
