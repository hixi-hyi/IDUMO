package jp.idumo.core.data;

import java.util.Map;
import java.util.TreeMap;

import jp.idumo.common.data.element.TextElement;
import jp.idumo.core.data.raw.RawDataType;


public interface DataElement {

	RawDataType get(String name);

	public abstract class AbstractData implements DataElement, TextElement {
		private Map<String, RawDataType> raw = new TreeMap<String, RawDataType>();

		public RawDataType add(RawDataType value) {
			return raw.put(value.getName(), value);
		}

		@Override
		public RawDataType get(String name) {
			return raw.get(name);
		}

		public String getSummary(String name) {
			return raw.get(name).getSummary();
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

		public Object getValue(String name) {
			return raw.get(name).getValue();
		}
	}

}
