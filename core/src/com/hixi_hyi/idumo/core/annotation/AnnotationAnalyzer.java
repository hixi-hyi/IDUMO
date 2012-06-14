package com.hixi_hyi.idumo.core.annotation;

import com.sun.javadoc.*;

public class AnnotationAnalyzer {
	public static boolean start(RootDoc root){
		ClassDoc[] classes = root.classes();
		for(ClassDoc doc:classes){
			System.out.println(doc);
		}
		return true;
	}
}
