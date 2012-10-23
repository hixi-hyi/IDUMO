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
package jp.idumo.common.parts.handler.raw;

import java.util.Map;
import java.util.TreeMap;

import jp.idumo.core.annotation.IDUMOHandler;
import jp.idumo.core.data.DataElement;
import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.ThroughElement;
import jp.idumo.core.data.connect.ArrayConnectDataType;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import jp.idumo.core.util.LogManager;
import jp.idumo.core.validator.ReceiveValidator;
import jp.idumo.core.validator.ReceiveValidatorSize;


/**
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 */
@IDUMOHandler(author = "Hiroyoshi HOUCHI", name = "指定した項目でソート", receive = DataElement.class, send = ThroughElement.class)
public class SortHandler implements Sendable, Receivable {
	private String name;
	private Sendable sender;
	private ReceiveValidator vSize = new ReceiveValidatorSize(1);
	
	public SortHandler(String name) {
		this.name = name;
	}
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		TreeMap<Object, DataElement> map = new TreeMap<Object, DataElement>();
		for (DataElement d : sender.onCall()) {
			map.put(d.get(name).getValue(), d);
			LogManager.debug(d.get(name).getValue());
		}
		FlowingData idf = new FlowingData();
		for (Map.Entry<Object, DataElement> e : map.entrySet()) {
			idf.add(e.getValue());
		}
		return idf;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(DataElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new ArrayConnectDataType(DataElement.class);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}
	
}
