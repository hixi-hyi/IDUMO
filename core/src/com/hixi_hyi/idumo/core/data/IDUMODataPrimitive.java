package com.hixi_hyi.idumo.core.data;

public abstract class IDUMODataPrimitive extends IDUMOData {
	protected static final String name = "value";

	public Object getValue(){
		return getValue(name);
	}

}
