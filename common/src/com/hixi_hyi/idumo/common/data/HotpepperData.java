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
package com.hixi_hyi.idumo.common.data;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRawNumber;
import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRawString;

public class HotpepperData extends IDUMOData {
	/*
	 * name - String
	 * address - String
	 * catch - String
	 * open - String
	 * budget - String
	 * average - String
	 */
	private static final String NAME = "name";
	private static final String KANA = "kana";
	private static final String LAT ="lat";
	private static final String LNG = "lng";
	private static final String ADDRESS = "address";
	private static final String CATCHCOPY = "catchcopy";
	private static final String OPEN = "budget";
	private static final String BUDGET = "budget";
	private static final String AVERAGE = "average";

	public HotpepperData(String name,String kana, double lat, double lng ,String address,String catchcopy, String open,String budget,String average) {
		add(new IDUMODataTypeRawString(NAME, name, "shop name"));
		add(new IDUMODataTypeRawString(KANA, kana, "shop kana name"));
		add(new IDUMODataTypeRawNumber(LAT, lat, "shop lat"));
		add(new IDUMODataTypeRawNumber(LNG, lng, "shop lng"));
		add(new IDUMODataTypeRawString(ADDRESS, address, "shop address"));
		add(new IDUMODataTypeRawString(CATCHCOPY, catchcopy, "shop catchcopy"));
		add(new IDUMODataTypeRawString(OPEN, open, "shop open"));
		add(new IDUMODataTypeRawString(BUDGET, budget, "shop budget"));
		add(new IDUMODataTypeRawString(AVERAGE, average, "shop average"));
	}

	public String getName(){
		return (String)getValue(NAME);
	}

	public String getKana(){
		return (String)getValue(KANA);
	}

	public double getLat(){
		return (Double)getValue(LAT);
	}

	public double getLng(){
		return (Double)getValue(LNG);
	}

	public String getAddress(){
		return (String)getValue(ADDRESS);
	}

	public String getCatchcopy(){
		return (String)getValue(CATCHCOPY);
	}

	public String getOpen(){
		return (String)getValue(OPEN);
	}

	public String getBudget(){
		return (String)getValue(BUDGET);
	}

	public String getAverage(){
		return (String)getValue(AVERAGE);
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(" : ");
		sb.append(getKana());
		sb.append(" : ");
		sb.append("[");
		sb.append(getLat());
		sb.append(",");
		sb.append(getLng());
		sb.append("]");
		sb.append(" : ");
		sb.append(getAddress());
		sb.append(" : ");
		sb.append(getCatchcopy());
		sb.append(" : ");
		sb.append(getOpen());
		sb.append(" : ");
		sb.append(getBudget());
		sb.append(" : ");
		sb.append(getAverage());
		return sb.toString();
	}


}
