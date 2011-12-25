package com.hixi_hyi.idumo.core.data;

import java.util.ArrayList;
import java.util.List;

public class DataType {
	public static List<Class<?>> generateDataType(Class<?>...classes){
		List<Class<?>> type = new ArrayList<Class<?>>();
		for(Class<?> c : classes){
			type.add(c);
		}
		return type;
	}
	
}
