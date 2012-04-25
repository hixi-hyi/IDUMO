package com.hixi_hyi.idumo.core.data.connect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hixi_hyi.idumo.core.data.IDUMOData;

public class IDUMODataTypeConnectArray implements IDUMODataTypeConnect {

	List<Class<? extends IDUMOData>> data = new ArrayList<Class<? extends IDUMOData>>();

	public IDUMODataTypeConnectArray(Class<? extends IDUMOData>... ds) {
		for (Class<? extends IDUMOData> d : ds) {
			data.add(d);
		}
	}

	@Override
	public Iterator<Class<? extends IDUMOData>> iterator() {
		return data.iterator();
	}

	@Override
	public boolean equals(IDUMODataTypeConnect connect) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
