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
package jp.idumo.common.parts.handler;

import java.util.List;

import jp.idumo.common.annotation.IDUMOCommon;
import jp.idumo.common.component.Hotpepper;
import jp.idumo.common.data.HotpepperData;
import jp.idumo.common.data.element.LatLngElement;
import jp.idumo.common.data.element.LatLngElement.LatLngData;
import jp.idumo.core.annotation.IDUMOHandler;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ArrayConnectDataType;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.util.LogManager;
import jp.idumo.core.validator.ReceiveValidatorSize;

/**
 * @author Hiroyoshi HOUCHI
 */
@IDUMOCommon
@IDUMOHandler(author = "Hiroyoshi HOUCHI", description = "現在地情報からHotpepperの情報を取得します", name = "Hotpepper", receive = LatLngElement.class, send = HotpepperData.class)
public class HotpepperHandler implements Sendable, Receivable {
	
	private Hotpepper				hotpepper	= new Hotpepper();
	private Sendable				sender;
	private ReceiveValidatorSize	vSize		= new ReceiveValidatorSize(1);
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public FlowingData onCall() {
		LogManager.log();
		LatLngElement gd = (LatLngElement) sender.onCall().next();
		hotpepper.setLatLon(gd.getLatitude(), gd.getLongitude());
		FlowingData p = new FlowingData();
		List<HotpepperData> data = hotpepper.getData();
		for (HotpepperData d : data) {
			p.add(d);
		}
		return p;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(LatLngData.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new ArrayConnectDataType(HotpepperData.class);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}
}
