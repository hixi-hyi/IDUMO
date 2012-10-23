package jp.idumo.core.doclet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.javadoc.AnnotationTypeDoc;
import com.sun.javadoc.AnnotationTypeElementDoc;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import com.sun.tools.javadoc.ClassDocImpl;

public class IDUMOVisualDoclet {
	private static final String ANNOTATION_PROVIDER = "IDUMOProvider";
	private static final String ANNOTATION_HANDLER = "IDUMOHandler";
	private static final String ANNOTATION_ADAPTOR = "IDUMOAdaptor";
	private static final String ANNOTATION_RECEIPTOR = "IDUMOReceiptor";
	
	public static boolean start(RootDoc root) throws FileNotFoundException, UnsupportedEncodingException {
		
		File file = new File(System.getProperty("user.dir")+"/idumoitem.json");
		System.out.println(file.getPath());
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		PrintWriter pw = new PrintWriter(osw);
		
		IDUMOTemplate provider = new IDUMOTemplate("provider");
		IDUMOTemplate handler = new IDUMOTemplate("handler");
		IDUMOTemplate receiptor = new IDUMOTemplate("receiptor");
		IDUMOTemplate adaptor = new IDUMOTemplate("adaptor");
		
		ClassDoc[] classes = root.classes();
		for (ClassDoc classDoc : classes) {
			// System.out.println(classDoc.toString());
			AnnotationDesc[] annotations = classDoc.annotations();
			for (AnnotationDesc annotation : annotations) {
				AnnotationTypeDoc typedoc = annotation.annotationType();
				if (typedoc.name().equals(ANNOTATION_PROVIDER)) {
					IDUMOItemData data = new IDUMOItemData(classDoc.toString(), annotation);
					provider.append(data);
				} else if (typedoc.name().equals(ANNOTATION_HANDLER)) {
					IDUMOItemData data = new IDUMOItemData(classDoc.toString(), annotation);
					handler.append(data);
				} else if (typedoc.name().equals(ANNOTATION_ADAPTOR)) {
					IDUMOItemData data = new IDUMOItemData(classDoc.toString(), annotation);
					adaptor.append(data);
				} else if (typedoc.name().equals(ANNOTATION_RECEIPTOR)) {
					IDUMOItemData data = new IDUMOItemData(classDoc.toString(), annotation);
					receiptor.append(data);
				}
			}
		}
		
		pw.println(provider.getJson());
		pw.println(handler.getJson());
		pw.println(adaptor.getJson());
		pw.println(receiptor.getJson());
		
		pw.close();
		return true;
	}
}

class IDUMOItemData {
	private static final String AUTHOR = "author";
	private static final String NAME = "name";
	private static final String DESC = "description";
	private static final String SEND = "send";
	private static final String RECEIVE = "receive";
	
	private String clazzName = "";
	private String author = "";
	private String desc = "";
	private String name = "";
	private String send = null;
	private String[] receives = null;
	private boolean isSend;
	private boolean isReceive;
	
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
			} else if (elementName.equals(DESC)) {
				// System.out.println("[desc:" + aValue.value().getClass());
				desc = (String) aValue.value();
			} else if (elementName.equals(SEND)) {
				send = aValue.value().toString();
				isSend = true;
			} else if (elementName.equals(RECEIVE)) {
				isReceive = true;
				Object rv = aValue.value();
				if (rv instanceof AnnotationValue[]) {
					AnnotationValue[] av = (AnnotationValue[]) aValue.value();
					receives = new String[av.length];
					for (int i = 0; i < av.length; i++) {
						receives[i] = av[i].value().toString();
					}
				} else if (rv instanceof ClassDocImpl) {
					receives = new String[1];
					receives[0] = rv.toString();
				}
			}
		}
	}
	
	public String getText() {
		String normal = String.format("package:'%s', display:'%s', author:'%s',description:'%s',", clazzName, name, author, desc);
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append(normal);
		if (isSend) {
			String sendParts = String.format("send:'%s', ", send);
			json.append(sendParts);
		}
		if (isReceive) {
			StringBuilder receive = new StringBuilder();
			receive.append("receive:[");
			for (String s : receives) {
				receive.append("'" + s + "'");
				receive.append(",");
			}
			receive.append("], ");
			json.append(receive.toString());
		}
		json.append("}");
		
		return json.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder receive = new StringBuilder();
		receive.append("{");
		for (String s : receives) {
			receive.append("'" + s + "'");
			receive.append(",");
		}
		receive.append("}");
		return String.format("{package:'%s', display:'%s', author:'%s',description:'%s', send:'%s', receive:%s}", clazzName, name, author, desc, send, receive.toString());
		// return
		// String.format("{package:'%s', display:'%s', author:'%s',description:'%s' }",
		// clazzName, name, author, desc);
	}
}

class IDUMOTemplate {
	private String name;
	private StringBuilder value = new StringBuilder();
	
	public IDUMOTemplate(String name) {
		this.name = name;
	}
	
	public void append(IDUMOItemData data) {
		value.append(data.getText());
		value.append(",\n");
		value.append("  ");
	}
	
	public String getJson() {
		return String.format("var %s = [\n  %s];", name, value.toString());
		
	}
}
