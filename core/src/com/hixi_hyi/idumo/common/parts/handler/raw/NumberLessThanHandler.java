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
package com.hixi_hyi.idumo.common.parts.handler.raw;

import com.hixi_hyi.idumo.core.annotation.IDUMOHandler;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.BoolPrimitiveElement;
import com.hixi_hyi.idumo.core.data.primitive.BoolPrimitiveElement.BoolPrimitiveData;
import com.hixi_hyi.idumo.core.data.primitive.NumberPrimitiveElement;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 */
@IDUMOHandler(author = "Hiroyoshi HOUCHI", name = "指定した数字より小さければ", receive = NumberPrimitiveElement.class, send = BoolPrimitiveData.class)
public class NumberLessThanHandler implements Sendable, Receivable {
	
	private Sendable sender;
	private double condition;
	private ReceiveValidatorSize validator = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1, NumberPrimitiveElement.class);
	
	public NumberLessThanHandler(double condition) {
		this.condition = condition;
	}
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		NumberPrimitiveElement number = (NumberPrimitiveElement) sender.onCall().next();
		double d = number.getNumber();
		// IDUMOLogManager.debug(d);
		// IDUMOLogManager.debug(String.format("raw:%.0f,con:%.0f",d,condition));
		if (d < condition) {
			return new FlowingData(new BoolPrimitiveElement.BoolPrimitiveData(true));
		}
		return new FlowingData(new BoolPrimitiveElement.BoolPrimitiveData(false));
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(NumberPrimitiveElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(BoolPrimitiveElement.class);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		validator.validate(senders);
		vType.validate(senders);
		sender = senders[0];
	}
}
