package jp.idumo.core.doclet.perser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.idumo.core.doclet.json.AnnotationArrayValue;
import jp.idumo.core.doclet.json.IJSONValue;
import jp.idumo.core.doclet.json.MapValue;
import jp.idumo.core.doclet.json.StringArrayValue;
import jp.idumo.core.doclet.json.StringKVListValue;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationTypeElementDoc;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.tools.javac.jvm.Items;

public class ConstructorAnnotation implements IAnnotation {
	
	public class ConstructorMeta {
		String	type;
		String	comment;
		
		boolean isCompleted() {
			return (type != null) && (comment != null);
		}
	}
	
	private static final String			TAG			= "arguments";
	private static final String			VALUE		= "value";
	private static final Set<String>	ALLOW_CLASS	= new HashSet<String>();
	
	private List<String> keys;
	private List<String> values;
	
	static {
		ALLOW_CLASS.add("String");
		ALLOW_CLASS.add("byte");
		ALLOW_CLASS.add("short");
		ALLOW_CLASS.add("int");
		ALLOW_CLASS.add("long");
		ALLOW_CLASS.add("char");
		ALLOW_CLASS.add("float");
		ALLOW_CLASS.add("double");
		
	}
	
	public ConstructorAnnotation(ConstructorDoc constructorDoc,AnnotationDesc annotation) {
		super();
		// System.out.println(doc.qualifiedName());
		Parameter[] parameters = constructorDoc.parameters();
		Map<String, ConstructorMeta> metas = new HashMap<String, ConstructorMeta>();
		List <String> parameterList = new ArrayList<String>();
		//Parameter [ public Constructor(String str,int i); ]
		for (Parameter parameter : parameters) {
			String name = parameter.name();
			String type = parameter.type().simpleTypeName();
			if (ALLOW_CLASS.contains(type)) {
				parameterList.add(type);
			}
//			// System.out.println("p| "+parameter);
//			// System.out.println("p| "+parameter.type().simpleTypeName());
//			// System.out.println("p| "+parameter.name());
		}
		
		List<String> annotationList = new ArrayList<String>();
		for (ElementValuePair elementValuePair : annotation.elementValues()) {
			AnnotationTypeElementDoc elementDoc = elementValuePair.element();
			AnnotationValue aValue = elementValuePair.value();
			String elementName = elementDoc.name();
			if (elementName.equals(VALUE)) {
				AnnotationArrayValue array = new AnnotationArrayValue(aValue);
				annotationList.addAll(array.toStringList());
			}
		}
		if(parameterList.size() != annotationList.size()){
			throw new RuntimeException();
		}
		keys = parameterList;
		values = annotationList;
		
		ParamTag[] paramTags = constructorDoc.paramTags();
//		//Tag [@IDUMOConstructor({"input","postnumber"}
//		for (ParamTag paramTag : paramTags) {
//			String name = paramTag.parameterName();
//			String comment = paramTag.parameterComment();
//			if (metas.containsKey(name)) {
//				metas.get(name).comment = comment;
//			}
//			// System.out.println("t| "+paramTag);
//			// System.out.println("t| "+name);
//			// System.out.println("t| "+comment);
//		}
//		for (Map.Entry<String, ConstructorMeta> meta : metas.entrySet()) {
//			ConstructorMeta value = meta.getValue();
//			if (value.isCompleted()) {
//				map.put(value.type, value.comment);
//			}
//		}
	}
	
	public boolean isEmpty() {
		return keys.isEmpty();
	}
	
	@Override
	public Map<String, IJSONValue> getKVMap() {
		Map<String, IJSONValue> params = new HashMap<String, IJSONValue>();
		params.put(TAG, new StringKVListValue(keys, values));
		return params;
	}
	
}
