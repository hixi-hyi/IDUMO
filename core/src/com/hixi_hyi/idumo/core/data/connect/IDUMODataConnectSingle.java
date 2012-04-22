package com.hixi_hyi.idumo.core.data.connect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hixi_hyi.idumo.core.data.IDUMOData;

public class IDUMODataConnectSingle implements IDUMODataConnect {

	List<Class<? extends IDUMOData>> data = new ArrayList<Class<? extends IDUMOData>>();
	
	public IDUMODataConnectSingle(Class<? extends IDUMOData> d){
		data.add(d);
	}

	@Override
	public Iterator<Class<? extends IDUMOData>> iterator() {
		return data.iterator();
	}

	@Override
	public boolean equals(IDUMODataConnect connect) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
}
