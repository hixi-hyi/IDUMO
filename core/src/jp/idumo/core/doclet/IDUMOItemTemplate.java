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
package jp.idumo.core.doclet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hiroyoshi HOUCHI
 */
public class IDUMOItemTemplate {
	private String				name;
	private static final String	SEPARATE	= ",\n";
	private List<JSONBuilder>	jsons		= new ArrayList<JSONBuilder>();
	
	public IDUMOItemTemplate(String name) {
		this.name = name;
	}
	
	public void add(JSONBuilder item) {
		jsons.add(item);
	}
	
	public String toJsonString() {
		StringBuilder sb = new StringBuilder();
		boolean isExec = false;
		for (JSONBuilder item : jsons) {
			isExec = true;
			sb.append("  ");
			sb.append(item.toString());
			sb.append(SEPARATE);
		}
		if (isExec) {
			sb.setLength(sb.length() - SEPARATE.length());
			return String.format("var %s =[\n%s\n];", name, sb.toString());
		}
		return "";
		
	}
}
