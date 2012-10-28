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
package jp.idumo.common.parts.provider;

import jp.idumo.common.annotation.IDUMOCommon;
import jp.idumo.core.annotation.IDUMOInfo;
import jp.idumo.core.annotation.IDUMOProvider;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.data.connect.SingleConnectDataType;
import jp.idumo.core.data.primitive.NumberPrimitiveElement;
import jp.idumo.core.parts.Sendable;

/**
 * @author Hiroyoshi HOUCHI
 */
@IDUMOCommon
@IDUMOProvider(send = NumberPrimitiveElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "指定した数字を送信", summary = "ユーザが指定した数字を送信")
public class NumberProvider implements Sendable {
	
	private Double	num;
	
	public NumberProvider(Double num) {
		this.num = num;
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public FlowingData onCall() {
		// IDUMOLogManager.log();
		FlowingData pipes = new FlowingData();
		pipes.add(new NumberPrimitiveElement.NumberPrimitiveData(num));
		return pipes;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(NumberPrimitiveElement.class);
	}
	
}
