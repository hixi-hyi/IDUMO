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
package jp.idumo.common.parts.adapter;

import jp.idumo.common.annotation.IDUMOCommon;
import jp.idumo.common.data.element.LatLngElement.LatLngData;
import jp.idumo.core.annotation.IDUMOAdaptor;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.MultiConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.data.primitive.NumberPrimitiveElement;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.validator.ReceiveValidator;
import jp.idumo.core.validator.ReceiveValidatorSize;
import jp.idumo.core.validator.ReceiveValidatorType;

/**
 * @author Hiroyoshi HOUCHI
 */
@IDUMOCommon
@IDUMOAdaptor(author = "Hiroyoshi HOUCHI", name = "Number->GPS", receive = { NumberPrimitiveElement.class, NumberPrimitiveElement.class }, send = LatLngData.class)
public class Number2GPSAdapter implements Sendable, Receivable {
	
	private Sendable			sender1;
	private Sendable			sender2;
	
	private ReceiveValidator	vSize	= new ReceiveValidatorSize(2);
	private ReceiveValidator	vType1	= new ReceiveValidatorType(1, NumberPrimitiveElement.class);
	private ReceiveValidator	vType2	= new ReceiveValidatorType(2, NumberPrimitiveElement.class);
	
	public Number2GPSAdapter() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		flag = flag && sender1.isReady();
		flag = flag && sender2.isReady();
		return flag;
	}
	
	@Override
	public FlowingData onCall() {
		NumberPrimitiveElement num1 = (NumberPrimitiveElement) sender1.onCall().next();
		NumberPrimitiveElement num2 = (NumberPrimitiveElement) sender2.onCall().next();
		LatLngData latlng = new LatLngData(num1.getNumber(), num2.getNumber());
		// IDUMOLogManager.debug(gd);
		return new FlowingData(latlng);
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new MultiConnectDataType(NumberPrimitiveElement.class, NumberPrimitiveElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(LatLngData.class);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		sender1 = senders[0];
		sender2 = senders[1];
	}
	
}
