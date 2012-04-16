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
import java.util.List;
import java.util.ListIterator;

public class IDUMOFlowingData implements Iterable<Object> {
	private ArrayList<Object>	datalist;
	{
		datalist = new ArrayList<Object>();
	}

	public static IDUMOFlowingData generatePipeData(Object...objects){
		return new IDUMOFlowingData(objects);
	}

	public IDUMOFlowingData() {}

	public IDUMOFlowingData(Object... objects) {
		for (Object o : objects) {
			datalist.add(o);
		}
	}

	public Collection<Object> getData() {
		return datalist;
	}

	public Collection<Class<?>> getDataType() {
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		for (Object o : datalist) {
			types.add(o.getClass());
		}
		return types;
	}

	// public Object poll() {
	// return remove(0);
	// }

	/**
	 * @param index
	 * @param object
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	public void add(int index, Object object) {
		datalist.add(index, object);
	}

	/**
	 * @param object
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(Object object) {
		return datalist.add(object);
	}

	/**
	 * @param collection
	 * @return
	 * @see java.util.ArrayList#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Object> collection) {
		return datalist.addAll(collection);
	}

	/**
	 * @param index
	 * @param collection
	 * @return
	 * @see java.util.ArrayList#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int index, Collection<? extends Object> collection) {
		return datalist.addAll(index, collection);
	}

	/**
	 *
	 * @see java.util.ArrayList#clear()
	 */
	public void clear() {
		datalist.clear();
	}

	/**
	 * @return
	 * @see java.util.ArrayList#clone()
	 */
	@Override
	public Object clone() {
		return datalist.clone();
	}

	/**
	 * @param object
	 * @return
	 * @see java.util.ArrayList#contains(java.lang.Object)
	 */
	public boolean contains(Object object) {
		return datalist.contains(object);
	}

	/**
	 * @param collection
	 * @return
	 * @see java.util.AbstractCollection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> collection) {
		return datalist.containsAll(collection);
	}

	/**
	 * @param minimumCapacity
	 * @see java.util.ArrayList#ensureCapacity(int)
	 */
	public void ensureCapacity(int minimumCapacity) {
		datalist.ensureCapacity(minimumCapacity);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return datalist.equals(o);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Object get(int index) {
		return datalist.get(index);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#hashCode()
	 */
	@Override
	public int hashCode() {
		return datalist.hashCode();
	}

	/**
	 * @param object
	 * @return
	 * @see java.util.ArrayList#indexOf(java.lang.Object)
	 */
	public int indexOf(Object object) {
		return datalist.indexOf(object);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#isEmpty()
	 */
	public boolean isEmpty() {
		return datalist.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.ArrayList#iterator()
	 */
	@Override
	public Iterator<Object> iterator() {
		return datalist.iterator();
	}

	/**
	 * @param object
	 * @return
	 * @see java.util.ArrayList#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object object) {
		return datalist.lastIndexOf(object);
	}

	/**
	 * @return
	 * @see java.util.AbstractList#listIterator()
	 */
	public ListIterator<Object> listIterator() {
		return datalist.listIterator();
	}

	/**
	 * @param location
	 * @return
	 * @see java.util.AbstractList#listIterator(int)
	 */
	public ListIterator<Object> listIterator(int location) {
		return datalist.listIterator(location);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#remove(int)
	 */
	public Object remove(int index) {
		return datalist.remove(index);
	}

	/**
	 * @param object
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	public boolean remove(Object object) {
		return datalist.remove(object);
	}

	/**
	 * @param collection
	 * @return
	 * @see java.util.AbstractCollection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> collection) {
		return datalist.removeAll(collection);
	}

	/**
	 * @param collection
	 * @return
	 * @see java.util.AbstractCollection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> collection) {
		return datalist.retainAll(collection);
	}

	/**
	 * @param index
	 * @param object
	 * @return
	 * @see java.util.ArrayList#set(int, java.lang.Object)
	 */
	public Object set(int index, Object object) {
		return datalist.set(index, object);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return datalist.size();
	}

	/**
	 * @param start
	 * @param end
	 * @return
	 * @see java.util.AbstractList#subList(int, int)
	 */
	public List<Object> subList(int start, int end) {
		return datalist.subList(start, end);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#toArray()
	 */
	public Object[] toArray() {
		return datalist.toArray();
	}

	/**
	 * @param <T>
	 * @param contents
	 * @return
	 * @see java.util.ArrayList#toArray(T[])
	 */
	public <T> T[] toArray(T[] contents) {
		return datalist.toArray(contents);
	}

	/**
	 * @return
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		return datalist.toString();
	}

	/**
	 *
	 * @see java.util.ArrayList#trimToSize()
	 */
	public void trimToSize() {
		datalist.trimToSize();
	}

}
