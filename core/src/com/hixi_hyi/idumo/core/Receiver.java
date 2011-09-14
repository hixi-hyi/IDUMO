package com.hixi_hyi.idumo.core;

/**
 * IdumoComponentのHandler,Receiptorを作成する際に実装してください
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface Receiver extends IdumoComponent {
	/**
	 * データを受け取るSender(Provider,Handlerに相当)をsetするメソッド
	 * 
	 * @param senders
	 * @return
	 * @throws IdumoException
	 */
	public boolean setSender(Sender... senders) throws IdumoException;
}
