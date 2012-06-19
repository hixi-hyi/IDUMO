package com.hixi_hyi.idumo.core.doclet;

import java.util.ArrayList;
import java.util.List;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.javadoc.AnnotationTypeDoc;
import com.sun.javadoc.AnnotationTypeElementDoc;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;

public class IDUMOVisualDoclet {
	private static final String	IDUMO_ANNOTATION	= "IDUMOItem";
	private static final String	RECEIPTOR			= "Receiptor";
	private static final String	PROVIDER			= "Provider";
	private static final String	HANDLER				= "Handler";
	private static final String	ADAPTOR				= "Adaptor";
	
	public static boolean start(RootDoc root) {
		
		List<IDUMOItemData> list = new ArrayList<IDUMOItemData>();
		
		ClassDoc[] classes = root.classes();
		for (ClassDoc classDoc : classes) {
			// System.out.println(classDoc.toString());
			AnnotationDesc[] annotations = classDoc.annotations();
			for (AnnotationDesc annotation : annotations) {
				AnnotationTypeDoc typedoc = annotation.annotationType();
				if (typedoc.name().equals(IDUMO_ANNOTATION)) {
					// System.out.println(typedoc);
					IDUMOItemData data = new IDUMOItemData(classDoc.toString(), annotation);
					list.add(data);
				}
				// for(ElementValuePair evp:annotation.elementValues()){
				// System.out.println(evp);
				// }
			}
		}
		
		IDUMOTemplate provider = new IDUMOTemplate("provider");
		IDUMOTemplate handler = new IDUMOTemplate("handler");
		IDUMOTemplate receiptor = new IDUMOTemplate("receiptor");
		IDUMOTemplate adaptor = new IDUMOTemplate("adaptor");
		
		for (IDUMOItemData data : list) {
			if (data.getType().equals(PROVIDER)) {
				provider.append(data);
			} else if (data.getType().equals(HANDLER)) {
				handler.append(data);
			} else if (data.getType().equals(RECEIPTOR)) {
				receiptor.append(data);
			} else if (data.getType().equals(ADAPTOR)) {
				adaptor.append(data);
			}
		}
		
		System.out.println(provider);
		System.out.println(handler);
		System.out.println(receiptor);
		System.out.println(adaptor);
		
		return true;
	}
}

class IDUMOTemplate {
	private String			name;
	private StringBuilder	value	= new StringBuilder();
	
	public IDUMOTemplate(String name) {
		this.name = name;
	}
	
	public void append(IDUMOItemData data) {
		value.append(data.toString());
		value.append(",\n");
	}
	
	@Override
	public String toString() {
		return String.format("var %s = [\n%s];", name, value.toString());
		
	}
}

class IDUMOItemData {
	private static final String	AUTHOR	= "author";
	private static final String	NAME	= "name";
	private static final String	TYPE	= "type";
	private static final String	DESC	= "description";
	private String				clazzName;
	private String				author;
	private String				desc;
	private String				name;
	private String				type;
	
	IDUMOItemData(String clazzName, AnnotationDesc annotation) {
		this.clazzName = clazzName;
		for (ElementValuePair elementValuePair : annotation.elementValues()) {
			AnnotationTypeElementDoc elementDoc = elementValuePair.element();
			AnnotationValue aValue = elementValuePair.value();
			String elementName = elementDoc.name();
			// System.out.println(elementDoc);
			// System.out.println(aValue.getClass());
			if (elementName.equals(AUTHOR)) {
				author = (String) aValue.value();
			} else if (elementName.equals(NAME)) {
				name = (String) aValue.value();
			} else if (elementName.equals(TYPE)) {
				FieldDoc fd = (FieldDoc) aValue.value();
				type = fd.name();
			} else if (elementName.equals(DESC)) {
				desc = (String) aValue.value();
			}
		}
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return String.format("{pname:'%s', vname:'%s', author:'%s',description:'%s' }", clazzName, name, author, desc);
	}
}
