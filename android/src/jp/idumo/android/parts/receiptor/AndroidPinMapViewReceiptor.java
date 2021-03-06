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

import java.util.ArrayList;
import java.util.List;

import jp.idumo.android.core.AndroidActivityController;
import jp.idumo.android.core.AndroidActivityResource;
import jp.idumo.common.data.element.LatLngElement;
import jp.idumo.core.data.DataElement;
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
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import jp.idumo.android.R;

/**
 * Android上にテキスト情報を出力するReceiptorです
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
@Deprecated
public class AndroidPinMapViewReceiptor implements Receivable, Executable,AndroidActivityController {
	
	private MapView view;
	private Sendable				sender;
	private ReceiveValidatorSize	vSize		= new ReceiveValidatorSize(1);
	
	private static final int		ZOOM_LEVEL	= 10;
	private MapController			controller;
	private DefaultItemizedOverlay	overlay;
	
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
		for (DataElement id : idf) {
			LatLngElement llde = (LatLngElement) id;
			GeoPoint point = new GeoPoint((int) (llde.getLatitude() * 1E6), (int) (llde.getLongitude() * 1E6));
//			overlay.addPoint(point);
			LogManager.debug(point);
		}
		view.invalidate();
	}
	
	@Override
	public void setSender(Sendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}

	@Override
	public void registActivity(AndroidActivityResource activity) {
		view = new MapView(activity.getActivity(),API_KEY);
		activity.getActivity().setContentView(view);
		
		controller = view.getController();
		controller.setZoom(ZOOM_LEVEL);
	
		Drawable marker = activity.getApplicationContext().getResources().getDrawable(R.drawable.androidmarker);
		overlay = new DefaultItemizedOverlay(marker);
		view.getOverlays().add(overlay);
		
		view.setClickable(true);
		view.setBuiltInZoomControls(true);
		view.setSatellite(false);		
	}
	
}

class DefaultItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private List<OverlayItem>	items	= new ArrayList<OverlayItem>();
	
	public DefaultItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public void addPoint(GeoPoint point) {
		addPoint(point, "", "");
	}
	
	public void addPoint(GeoPoint point, String title, String snippet) {
		items.add(new OverlayItem(point, title, snippet));
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return items.get(i);
	}
	
	@Override
	public int size() {
		return items.size();
	}
}
