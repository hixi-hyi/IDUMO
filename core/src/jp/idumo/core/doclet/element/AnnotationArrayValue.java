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
package jp.idumo.core.doclet.element;

import com.sun.javadoc.AnnotationValue;
import com.sun.tools.javadoc.ClassDocImpl;

/**
 * @author Hiroyoshi HOUCHI
 */
public class AnnotationArrayValue implements IJSONValue {
	
	private AnnotationValue	value;
	
	public AnnotationArrayValue(AnnotationValue value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Object rv = value.value();
		if (rv instanceof AnnotationValue[]) {
			AnnotationValue[] av = (AnnotationValue[]) value.value();
			for (AnnotationValue element : av) {
				sb.append(JSON_STRING_DELIMITER);
				sb.append(element.value().toString());
				sb.append(JSON_STRING_DELIMITER);
				sb.append(",");
			}
			sb.setLength(sb.length() - 1);
		} else if (rv instanceof ClassDocImpl) {
			sb.append(JSON_STRING_DELIMITER);
			sb.append(rv.toString());
			sb.append(JSON_STRING_DELIMITER);
		}
		sb.append("]");
		return sb.toString();
	}
	
}
