package com.hixi_hyi.idumo.core.data;

public abstract class IDUMODataPrimitive extends IDUMODataBase {
	protected static final String	NAME	= "value";

	public Object getValue() {
		return getValue(NAME);
	}

}
