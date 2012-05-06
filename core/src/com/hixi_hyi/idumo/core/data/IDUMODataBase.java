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
package com.hixi_hyi.idumo.core.data;

import java.util.Map;
import java.util.TreeMap;

import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRaw;

public abstract class IDUMODataBase implements IDUMOData{
	private Map<String, IDUMODataTypeRaw>	raw	= new TreeMap<String, IDUMODataTypeRaw>();
	
	public IDUMODataTypeRaw add(IDUMODataTypeRaw value) {
		// IDUMOLogManager.debug(value.getName());
		// IDUMOLogManager.debug(value.getValue());
		return raw.put(value.getName(), value);
	}
	
	public Object getValue(String name) {
		// IDUMOLogManager.debug(NAME);
		// IDUMOLogManager.debug(raw.get(NAME));
		// IDUMOLogManager.debug(raw.get(NAME).getValue());
		return raw.get(name).getValue();
	}
	
	public String getSummary(String name) {
		return raw.get(name).getSummary();
	}
	
	public IDUMODataTypeRaw get(String name) {
		return raw.get(name);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("IDUMOData{");
		for (Map.Entry<String, IDUMODataTypeRaw> entry : raw.entrySet()) {
			sb.append("[\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append(entry.getValue().getValue());
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}
}
