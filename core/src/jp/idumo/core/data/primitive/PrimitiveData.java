package jp.idumo.core.data.primitive;

import jp.idumo.core.data.DataElement.AbstractData;

public abstract class PrimitiveData extends AbstractData {
	protected static final String NAME = "value";
	
	public Object getValue() {
		return getValue(NAME);
	}
	
}
