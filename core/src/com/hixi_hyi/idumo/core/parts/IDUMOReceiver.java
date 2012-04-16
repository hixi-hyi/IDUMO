package com.hixi_hyi.idumo.core.parts;

import com.hixi_hyi.idumo.core.exception.IDUMOException;

/**
 * IdumoComponentのHandler,Receiptorを作成する際に実装してください
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface IDUMOReceiver extends IDUMOParts {
	/**
	 * データを受け取るSender(Provider,Handlerに相当)をsetするメソッド
	 * 
	 * @param senders
	 * @return
	 * @throws IDUMOException
	 */
	public boolean setSender(IDUMOSender... senders) throws IDUMOException;
}
