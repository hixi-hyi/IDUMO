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
package jp.idumo.core.doclet.perser.special;

import jp.idumo.core.doclet.json.AnnotationEnumArrayValue;
import jp.idumo.core.doclet.json.EnumArrayValue;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.javadoc.AnnotationTypeElementDoc;
import com.sun.javadoc.AnnotationValue;

/**
 * @author Hiroyoshi HOUCHI
 */
public class AndroidAnnotation extends SpecicalAnnotation {
	private static final String	TYPE		= "android";
	private static final String	LIBRARIES	= "libraries";
	private static final String	FEATURES	= "features";
	private static final String	PERMISSIONS	= "permissions";
	
	public AndroidAnnotation(AnnotationDesc annotation) {
		super();
		for (ElementValuePair elementValuePair : annotation.elementValues()) {
			AnnotationTypeElementDoc elementDoc = elementValuePair.element();
			AnnotationValue aValue = elementValuePair.value();
			String elementName = elementDoc.name();
			if (elementName.equals(LIBRARIES)) {
				items.put(LIBRARIES, new AnnotationEnumArrayValue(aValue));
			} else if (elementName.equals(FEATURES)) {
				items.put(FEATURES, new AnnotationEnumArrayValue(aValue));
			} else if (elementName.equals(PERMISSIONS)) {
				items.put(PERMISSIONS, new AnnotationEnumArrayValue(aValue));
			}
		}
	}
	
	private AndroidAnnotation() {}
	
	public static AndroidAnnotation getDefault() {
		AndroidAnnotation annotation = new AndroidAnnotation();
		EnumArrayValue library = new EnumArrayValue("jp.idumo.android.manifest.AndroidLibrary");
		library.add("MAPS");
		EnumArrayValue permission = new EnumArrayValue("jp.idumo.android.manifest.AndroidPermission");
		permission.add("INTERNET");
		annotation.items.put(LIBRARIES, library);
		annotation.items.put(PERMISSIONS, permission);
		return annotation;
	}
	
	@Override
	String getType() {
		return TYPE;
	}
	
}
