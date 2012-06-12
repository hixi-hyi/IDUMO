package com.hixi_hyi.idumo.core.data.connect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hixi_hyi.idumo.core.data.Data;

public class ArrayConnectDataType implements ConnectDataType {
	
	List<Class<? extends Data>> data = new ArrayList<Class<? extends Data>>();
	
	public ArrayConnectDataType(Class<? extends Data> d) {
		data.add(d);
	}
	
	@Override
	public boolean equals(ConnectDataType connect) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	@Override
	public Iterator<Class<? extends Data>> iterator() {
		return data.iterator();
	}
	
}
