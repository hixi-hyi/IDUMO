package com.hixi_hyi.android.idumo.receiptor;

import com.hixi_hyi.android.idumo.IdumoInterface;
import com.hixi_hyi.android.idumo.data.PipeData;
import com.hixi_hyi.android.idumo.eventlistener.HandlerListener;

public interface ReceiptorInterface extends IdumoInterface,HandlerListener {
	/**
	 * 指定されたパラメータを設定するメソッド
	 * @param parameter
	 */
	public void setParameter(PipeData parameter);

	/**
	 * 指定されたパラメータがこのReceiptorに設定できるか調べるメソッド
	 * @param parameter
	 * @return
	 */
	public boolean isAccesibleParameter(PipeData parameter);
}
