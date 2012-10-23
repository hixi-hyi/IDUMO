package jp.idumo.core.data.connect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.idumo.core.data.DataElement;


public class ArrayConnectDataType implements ConnectDataType {
	
	List<Class<? extends DataElement>> data = new ArrayList<Class<? extends DataElement>>();
	
	public ArrayConnectDataType(Class<? extends DataElement> d) {
		data.add(d);
	}
	
	@Override
	public boolean equals(ConnectDataType connect) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	@Override
	public Iterator<Class<? extends DataElement>> iterator() {
		return data.iterator();
	}
	
}
