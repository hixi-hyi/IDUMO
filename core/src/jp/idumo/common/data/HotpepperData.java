/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jp.idumo.common.data;

import jp.idumo.common.data.element.LatLngElement;
import jp.idumo.common.data.element.TextElement;
import jp.idumo.core.data.DataElement.AbstractData;
import jp.idumo.core.data.raw.NumberRawDataType;
import jp.idumo.core.data.raw.StringRawDataType;

public class HotpepperData extends AbstractData implements LatLngElement, TextElement {
	private static final String NAME = "name";
	private static final String KANA = "kana";
	private static final String LAT = "lat";
	private static final String LNG = "lng";
	private static final String ADDRESS = "address";
	private static final String CATCHCOPY = "catchcopy";
	private static final String OPEN = "budget";
	private static final String BUDGET = "budget";
	private static final String AVERAGE = "average";
	
	public HotpepperData(String name, String kana, double lat, double lng, String address, String catchcopy, String open, String budget, String average) {
		add(new StringRawDataType(NAME, name, "shop name"));
		add(new StringRawDataType(KANA, kana, "shop kana name"));
		add(new NumberRawDataType(LAT, lat, "shop lat"));
		add(new NumberRawDataType(LNG, lng, "shop lng"));
		add(new StringRawDataType(ADDRESS, address, "shop address"));
		add(new StringRawDataType(CATCHCOPY, catchcopy, "shop catchcopy"));
		add(new StringRawDataType(OPEN, open, "shop open"));
		add(new StringRawDataType(BUDGET, budget, "shop budget"));
		add(new StringRawDataType(AVERAGE, average, "shop average"));
	}
	
	public String getAddress() {
		return (String) getValue(ADDRESS);
	}
	
	public String getAverage() {
		return (String) getValue(AVERAGE);
	}
	
	public String getBudget() {
		return (String) getValue(BUDGET);
	}
	
	public String getCatchcopy() {
		return (String) getValue(CATCHCOPY);
	}
	
	public String getKana() {
		return (String) getValue(KANA);
	}
	
	@Override
	public double getLatitude() {
		return (Double) getValue(LAT);
	}
	
	@Override
	public double getLongitude() {
		return (Double) getValue(LNG);
	}
	
	public String getName() {
		return (String) getValue(NAME);
	}
	
	public String getOpen() {
		return (String) getValue(OPEN);
	}
	
	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(":");
		sb.append(getAverage());
		// sb.append(getName());
		// sb.append(" : ");
		// sb.append(getKana());
		// sb.append(" : ");
		// sb.append("[");
		// sb.append(getLatitude());
		// sb.append(",");
		// sb.append(getLongitude());
		// sb.append("]");
		// sb.append(" : ");
		// sb.append(getAddress());
		// sb.append(" : ");
		// sb.append(getCatchcopy());
		// sb.append(" : ");
		// sb.append(getOpen());
		// sb.append(" : ");
		// sb.append(getBudget());
		// sb.append(" : ");
		// sb.append(getAverage());
		return sb.toString();
	}
	
}
