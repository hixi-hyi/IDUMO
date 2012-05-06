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
package com.hixi_hyi.idumo.common.handler.raw;

import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveBool;
import com.hixi_hyi.idumo.core.data.IDUMODataPrimitiveNumber;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnectSingle;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.IDUMOReceiveValidatorType;

/**
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 */
public class NumberLessThanHandler implements IDUMOSendable, IDUMOReceivable {
	
	private IDUMOSendable			sender;
	private double					condition;
	private IDUMOReceiveValidatorSize	validator	= new IDUMOReceiveValidatorSize(1);
	private IDUMOReceiveValidatorType	vType		= new IDUMOReceiveValidatorType(1, IDUMODataPrimitiveNumber.class);
	
	public NumberLessThanHandler(double condition) {
		this.condition = condition;
	}
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		validator.validate(senders);
		vType.validate(senders);
		this.sender = senders[0];
	}
	
	@Override
	public IDUMODataFlowing onCall() {
		IDUMODataPrimitiveNumber number = (IDUMODataPrimitiveNumber) sender.onCall().next();
		double d = number.getNumber();
		// IDUMOLogManager.debug(d);
		// IDUMOLogManager.debug(String.format("raw:%.0f,con:%.0f",d,condition));
		if (d < condition) {
			return new IDUMODataFlowing(new IDUMODataPrimitiveBool(true));
		}
		return new IDUMODataFlowing(new IDUMODataPrimitiveBool(false));
	}
	
	@Override
	public IDUMODataTypeConnect receivableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveNumber.class);
	}
	
	@Override
	public IDUMODataTypeConnect sendableType() {
		return new IDUMODataTypeConnectSingle(IDUMODataPrimitiveBool.class);
	}
}
