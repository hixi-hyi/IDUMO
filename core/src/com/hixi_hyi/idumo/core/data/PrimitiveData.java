package com.hixi_hyi.idumo.core.data;

import com.hixi_hyi.idumo.core.data.Data.IDUMODataBase;
public abstract class PrimitiveData extends IDUMODataBase {
	protected static final String	NAME	= "value";

	public Object getValue() {
		return getValue(NAME);
	}

}
