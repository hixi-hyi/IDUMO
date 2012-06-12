package com.hixi_hyi.idumo.core.data.primitive;

import com.hixi_hyi.idumo.core.data.Data.AbstractData;

public abstract class PrimitiveData extends AbstractData {
	protected static final String NAME = "value";
	
	public Object getValue() {
		return getValue(NAME);
	}
	
}
