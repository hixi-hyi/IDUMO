package com.hixi_hyi.idumo.common.data.element;

import com.hixi_hyi.idumo.core.data.Data.AbstractData;
import com.hixi_hyi.idumo.core.data.raw.StringRawDataType;

public interface TextElement {
	public String getText();
	
	public class TextData extends AbstractData implements TextElement {
		private static final String TEXT = "text";
		
		public TextData(String text) {
			add(new StringRawDataType(TEXT, text, "text"));
		}
		
		@Override
		public String getText() {
			return (String) getValue(TEXT);
		}
		
	}
}
