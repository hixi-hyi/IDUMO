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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import jp.idumo.core.doclet.perser.CoreAnnotation;
import jp.idumo.core.doclet.perser.special.AndroidAnnotation;
import jp.idumo.core.doclet.perser.special.CommonAnnotation;
import jp.idumo.core.doclet.perser.special.ConsoleAnnotation;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

/**
 * @author Hiroyoshi HOUCHI
 */
public class IDUMOVisualDoclet {
	private static final String	JSON_NAME	= "idumoitem.json";
	
	private static final String	COMMON		= "IDUMOCommon";
	private static final String	ANDROID		= "IDUMOAndroid";
	private static final String	CONSOLE		= "IDUMOConsole";
	
	private static final String	PROVIDER	= "IDUMOProvider";
	private static final String	HANDLER		= "IDUMOHandler";
	private static final String	ADAPTOR		= "IDUMOAdaptor";
	private static final String	RECEIPTOR	= "IDUMOReceiptor";
	
	public static boolean start(RootDoc root) throws FileNotFoundException, UnsupportedEncodingException {
		
		File file = new File(System.getProperty("user.dir") + "/" + JSON_NAME);
		System.out.println(file.getPath());
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		PrintWriter pw = new PrintWriter(osw);
		
		IDUMOItemTemplate provider = new IDUMOItemTemplate("provider");
		IDUMOItemTemplate handler = new IDUMOItemTemplate("handler");
		IDUMOItemTemplate receiptor = new IDUMOItemTemplate("receiptor");
		IDUMOItemTemplate adaptor = new IDUMOItemTemplate("adaptor");
		
		boolean isProvider, isHandler, isAdaptor, isReceiptor;
		
		ClassDoc[] classes = root.classes();
		for (ClassDoc classDoc : classes) {
			String classname = classDoc.toString();
			isProvider = isHandler = isAdaptor = isReceiptor = false;
			// System.out.println(classDoc.toString());
			AnnotationDesc[] annotations = classDoc.annotations();
			JSONBuilder json = new JSONBuilder();
			for (AnnotationDesc annotation : annotations) {
				// System.out.println("annotation:" + annotation);
				String typename = annotation.annotationType().name();
				// System.out.println("typedoc   :" + typedoc);
				// System.out.println("typename  :" + typedoc.name());
				
				// IDUMOCore
				if (typename.equals(PROVIDER)) {
					isProvider = true;
					json.add(new CoreAnnotation(classname, annotation));
				} else if (typename.equals(HANDLER)) {
					isHandler = true;
					json.add(new CoreAnnotation(classname, annotation));
				} else if (typename.equals(ADAPTOR)) {
					isAdaptor = true;
					json.add(new CoreAnnotation(classname, annotation));
				} else if (typename.equals(RECEIPTOR)) {
					isReceiptor = true;
					json.add(new CoreAnnotation(classname, annotation));
				}
				
				if (typename.equals(COMMON)) {
					json.add(new CommonAnnotation(annotation));
				} else if (typename.equals(ANDROID)) {
					json.add(new AndroidAnnotation(annotation));
				} else if (typename.equals(CONSOLE)) {
					json.add(new ConsoleAnnotation(annotation));
				}
			}
			if (isProvider) {
				provider.add(json);
			} else if (isHandler) {
				handler.add(json);
			} else if (isAdaptor) {
				adaptor.add(json);
			} else if (isReceiptor) {
				receiptor.add(json);
			}
		}
		
		pw.println(provider.getJson());
		pw.println(handler.getJson());
		pw.println(adaptor.getJson());
		pw.println(receiptor.getJson());
		
		pw.close();
		return true;
	}
	
	public static CoreAnnotation getIDUMOItemData(String classname, AnnotationDesc[] annotations) {
		
		return null;
	}
	
}
