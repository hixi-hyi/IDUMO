/**
 * Copyright (uc) <2012>, <Hiroyoshi Houchi> All rights reserved.
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import jp.idumo.core.doclet.json.StringArrayValue;
import jp.idumo.core.doclet.json.StringValue;
import jp.idumo.core.doclet.perser.ConnectAnnotation;
import jp.idumo.core.doclet.perser.ConstructorAnnotation;
import jp.idumo.core.doclet.perser.InfoAnnotation;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.RootDoc;

/**
 * @author Hiroyoshi HOUCHI
 */
public class CreatePartsFile {
	private static final String	ENCODING		= "UTF-8";
	private static final String	ITEM_JSON_NAME	= "idumoparts.json";
	private static final String	I_INFO			= "IDUMOInfo";
	private static final String	I_CONSTRUCTOR	= "IDUMOConstructor";
	
	private static final String	I_COMMON		= "IDUMOCommon";
	private static final String	I_ANDROID		= "IDUMOAndroid";
	private static final String	I_CONSOLE		= "IDUMOConsole";
	
	private static final String	I_PROVIDER		= "IDUMOProvider";
	private static final String	I_HANDLER		= "IDUMOHandler";
	private static final String	I_ADAPTOR		= "IDUMOAdaptor";
	private static final String	I_RECEIPTOR		= "IDUMOReceiptor";
	
	public static boolean start(RootDoc root) throws FileNotFoundException, UnsupportedEncodingException {
		
		File idumoitem = new File(System.getProperty("user.dir") + "/" + ITEM_JSON_NAME);
		System.out.println(idumoitem.getPath());
		PrintWriter pwItem = new PrintWriter(new OutputStreamWriter(new FileOutputStream(idumoitem), ENCODING));
		
		boolean isItem;
		
		JSONArrayTemplate template = new JSONArrayTemplate();
//		IDUMOItemTemplate template = new IDUMOItemTemplate("idumoparts");
		
		ClassDoc[] classes = root.classes();
		for (ClassDoc classDoc : classes) {
			String classname = classDoc.toString();
			isItem = false;
			// System.out.println(classDoc.toString());
			AnnotationDesc[] annotations = classDoc.annotations();
			JSONBuilder json = new JSONBuilder();
			// Class
			for (AnnotationDesc annotation : annotations) {
				// System.out.println("annotation:" + annotation);
				String typename = annotation.annotationType().name();
				// System.out.println("typedoc   :" + typedoc);
				// System.out.println("typename  :" + typedoc.name());
				
				// Info
				if (typename.equals(I_INFO)) {
					isItem = true;
					json.add(new InfoAnnotation(classname, annotation));
				}
				
				// Connect
				if (typename.equals(I_PROVIDER)) {
					json.add("type", new StringValue("provider"));
					json.add(new ConnectAnnotation(annotation));
				} else if (typename.equals(I_HANDLER)) {
					json.add("type", new StringValue("handler"));
					json.add(new ConnectAnnotation(annotation));
				} else if (typename.equals(I_ADAPTOR)) {
					json.add("type", new StringValue("adaptor"));
					json.add(new ConnectAnnotation(annotation));
				} else if (typename.equals(I_RECEIPTOR)) {
					json.add("type", new StringValue("receiptor"));
					json.add(new ConnectAnnotation(annotation));
				}
				
				if (typename.equals(I_COMMON)) {
					json.add("platform", new StringArrayValue("android", "console"));
				} else if (typename.equals(I_ANDROID)) {
					json.add("platform", new StringArrayValue("android"));
				} else if (typename.equals(I_CONSOLE)) {
					json.add("platform", new StringArrayValue("console"));
				}
			}
			// Constructer
			if (isItem) {
				ConstructorDoc[] constructors = classDoc.constructors();
				for (ConstructorDoc constructor : constructors) {
					for (AnnotationDesc annotation : constructor.annotations()) {
						if (annotation.annotationType().name().equals(I_CONSTRUCTOR)) {
							json.add(new ConstructorAnnotation(constructor, annotation));
						}
						
					}
				}
			}
			if (isItem) {
				template.add(json);
			}
		}
		
		pwItem.println(template.toJsonString());
		
		pwItem.close();
		return true;
	}
	
}
