package com.hixi_hyi.android.receiptor;

import com.hixi_hyi.android.IdumoInterface;
import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.eventlistener.HandlerListener;

public interface DataReceiptorInterface extends IdumoInterface,HandlerListener {
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
