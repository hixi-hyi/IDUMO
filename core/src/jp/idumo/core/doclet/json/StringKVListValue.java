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
package jp.idumo.core.doclet.json;

import java.util.List;

/**
 * @author Hiroyoshi HOUCHI
 */
public class StringKVListValue implements IJSONValue {
	
	private List<String> keys;
	private List<String> values;
	
	
	public StringKVListValue(List<String> keys,List<String> values) {
		if(keys.size()!=values.size()){
			throw new RuntimeException();
		}
		this.keys = keys;
		this.values = values;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean isExec = false;
		for(int i=0;i<keys.size();i++){
			isExec = true;
			sb.append("{");
			sb.append(JSON_STRING_DELIMITER);
			sb.append(keys.get(i));
			sb.append(JSON_STRING_DELIMITER);
			sb.append(":");
			sb.append(JSON_STRING_DELIMITER);
			sb.append(values.get(i));
			sb.append(JSON_STRING_DELIMITER);
			sb.append("},");
		}
		if (isExec) {
			sb.setLength(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}
	
}
