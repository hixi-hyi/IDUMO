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
package com.hixi_hyi.idumo.common.handler;

import com.hixi_hyi.idumo.core.data.Data;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.SingleConnectDataType;
import com.hixi_hyi.idumo.core.data.primitive.StringPrimitiveData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.validator.ReceiveValidator;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

/**
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
public class StringConcatHandler_Suffix implements Sendable, Receivable {
	
	private Sendable provider;
	private String fixWord;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	
	public StringConcatHandler_Suffix(String fixWord) {
		this.fixWord = fixWord;
	}
	
	@Override
	public boolean isReady() {
		return provider.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		String s = ((StringPrimitiveData) provider.onCall().next()).getString();
		return new FlowingData(new StringPrimitiveData(s + fixWord));
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(Data.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(StringPrimitiveData.class);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		provider = senders[0];
	}
	
}
