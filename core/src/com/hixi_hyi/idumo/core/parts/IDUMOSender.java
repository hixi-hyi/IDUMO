package com.hixi_hyi.idumo.core.parts;

import java.util.List;

import com.hixi_hyi.idumo.core.data.PipeData;
import com.hixi_hyi.idumo.core.exception.IDUMOException;

/**
 * IdumoのProvider,Handlerを作成するときに実装してください
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface IDUMOSender extends IDUMOParts {
	/**
	 * getDataで取得できるPipeData(List)に保存されているデータタイプ(Class)を返す
	 * 
	 * @return getDataのClass
	 * @throws IDUMOException
	 */
	public List<Class<?>> getDataType() throws IDUMOException;
	
	/**
	 * データを取得する際に呼び出されるメソッド
	 * 
	 * @return
	 */
	public PipeData getData();
}
