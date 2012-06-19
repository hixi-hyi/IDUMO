package com.hixi_hyi.idumo.common.data.element;

import com.hixi_hyi.idumo.core.data.DataElement;
import com.hixi_hyi.idumo.core.data.raw.StringRawDataType;

public interface TextElement extends DataElement {
	public String getText();

	public class TextData extends AbstractData implements TextElement {
		private static final String	TEXT	= "text";

		public TextData(String text) {
			add(new StringRawDataType(TEXT, text, "text"));
		}

		@Override
		public String getText() {
			return (String) getValue(TEXT);
		}

	}
}
