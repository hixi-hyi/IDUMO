package com.hixi_hyi.idumo.core;

import java.util.List;

import com.hixi_hyi.idumo.core.data.PipeData;

/**
 * IdumoのProvider,Handlerを作成するときに実装してください
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface Sender extends IdumoComponent {
	/**
	 * getDataで取得できるPipeData(List)に保存されているデータタイプ(Class)を返す
	 * 
	 * @return getDataのClass
	 * @throws IdumoException
	 */
	public List<Class<?>> getDataType() throws IdumoException;
	
	/**
	 * データを取得する際に呼び出されるメソッド
	 * 
	 * @return
	 */
	public PipeData getData();
}
