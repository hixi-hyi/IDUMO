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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import jp.idumo.core.doclet.perser.DataStructure;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

/**
 * @author Hiroyoshi HOUCHI
 */
public class CreateDataStructureFile {
	private static final String	ENCODING				= "UTF-8";
	private static final String	DATASTRUCTURE_JSON_NAME	= "datastructure.json";
	
	public static boolean start(RootDoc root) throws IOException {
		
		File android = new File(System.getProperty("user.dir") + "/" + DATASTRUCTURE_JSON_NAME);
		PrintWriter pwAndroid = new PrintWriter(new OutputStreamWriter(new FileOutputStream(android), ENCODING));
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		ClassDoc[] classes = root.classes();
		for (ClassDoc classDoc : classes) {
			String classname = classDoc.toString();
			DataStructure ds = new DataStructure(classDoc);
			if (ds.isContainDataElement()) {
				JSONBuilder json = new JSONBuilder();
				json.add(ds);
				builder.append(String.format("  \"%s\":%s,\n", classname, json));
			}
		}
		builder.setLength(builder.length() - 2);
		builder.append("\n}");
		pwAndroid.println(builder);
		pwAndroid.close();
		return true;
	}
	
}
