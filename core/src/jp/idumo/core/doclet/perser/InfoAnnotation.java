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
package jp.idumo.core.doclet.perser;

import java.util.HashMap;
import java.util.Map;

import jp.idumo.core.doclet.json.AnnotationArrayValue;
import jp.idumo.core.doclet.json.AnnotationSingleValue;
import jp.idumo.core.doclet.json.IJSONValue;
import jp.idumo.core.doclet.json.StringValue;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.javadoc.AnnotationTypeElementDoc;
import com.sun.javadoc.AnnotationValue;

/**
 * @author Hiroyoshi HOUCHI
 */
public class InfoAnnotation implements IAnnotation {
	private static final String		AUTHOR	= "author";
	private static final String		DISPLAY	= "display";
	private static final String		SUMMARY	= "summary";
	private static final String		SEND	= "send";
	private static final String		RECEIVE	= "receive";
	
	private Map<String, IJSONValue>	items	= new HashMap<String, IJSONValue>();
	
	public InfoAnnotation(String clazzName, AnnotationDesc annotation) {
		items.put("package", new StringValue(clazzName));
		for (ElementValuePair elementValuePair : annotation.elementValues()) {
			AnnotationTypeElementDoc elementDoc = elementValuePair.element();
			AnnotationValue aValue = elementValuePair.value();
			String elementName = elementDoc.name();
			if (elementName.equals(AUTHOR)) {
				items.put(AUTHOR, new AnnotationSingleValue(aValue));
			} else if (elementName.equals(DISPLAY)) {
				items.put("display", new AnnotationSingleValue(aValue));
			} else if (elementName.equals(SUMMARY)) {
				items.put(SUMMARY, new AnnotationSingleValue(aValue));
			} else if (elementName.equals(SEND)) {
				items.put(SEND, new AnnotationSingleValue(aValue));
			} else if (elementName.equals(RECEIVE)) {
				items.put(RECEIVE, new AnnotationArrayValue(aValue));
			}
		}
	}
	
	@Override
	public Map<String, IJSONValue> getKVMap() {
		return items;
	}
	
}
