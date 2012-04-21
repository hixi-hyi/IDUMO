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
package com.hixi_hyi.idumo.core.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IDUMOFlowingData implements Iterable<IDUMOData> {
	private ArrayList<IDUMOData> datalist;
	{
		datalist = new ArrayList<IDUMOData>();
	}

	public static IDUMOFlowingData generatePipeData(IDUMOData... objects) {
		return new IDUMOFlowingData(objects);
	}

	public IDUMOFlowingData() {
	}

	public IDUMOFlowingData(IDUMOData... objects) {
		for (IDUMOData o : objects) {
			datalist.add(o);
		}
	}

	public Collection<IDUMOData> getData() {
		return datalist;
	}

	public Collection<Class<?>> getDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		for (Object o : datalist) {
			types.add(o.getClass());
		}
		return types;
	}

	/**
	 * @param object
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(IDUMOData object) {
		return datalist.add(object);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public IDUMOData get(int index) {
		return datalist.get(index);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return datalist.size();
	}

	/**
	 * @return
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		return datalist.toString();
	}

	@Override
	public Iterator<IDUMOData> iterator() {
		return datalist.iterator();
	}

	public IDUMOData next() {
		return datalist.remove(0);
	}

}
