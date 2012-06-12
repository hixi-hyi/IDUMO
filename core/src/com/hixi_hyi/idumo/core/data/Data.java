package com.hixi_hyi.idumo.core.data;

import java.util.Map;
import java.util.TreeMap;

import com.hixi_hyi.idumo.core.data.element.TextElement;
import com.hixi_hyi.idumo.core.data.raw.RawDataType;

public interface Data {

	RawDataType get(String name);

	public abstract class AbstractData implements Data,TextElement{
		private Map<String, RawDataType>	raw	= new TreeMap<String, RawDataType>();
		
		public RawDataType add(RawDataType value) {
			return raw.put(value.getName(), value);
		}
		
		public Object getValue(String name) {
			return raw.get(name).getValue();
		}
		
		public String getSummary(String name) {
			return raw.get(name).getSummary();
		}
		
		public RawDataType get(String name) {
			return raw.get(name);
		}
		
		@Override
		public String getText() {
			StringBuilder sb = new StringBuilder();
			sb.append("IDUMOData{");
			for (Map.Entry<String, RawDataType> entry : raw.entrySet()) {
				sb.append("[\"");
				sb.append(entry.getKey());
				sb.append("\":");
				sb.append(entry.getValue().getValue());
				sb.append("]");
			}
			sb.append("}");
			return sb.toString();
		}
	}

}
