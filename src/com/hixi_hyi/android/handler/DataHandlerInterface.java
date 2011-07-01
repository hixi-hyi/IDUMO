package com.hixi_hyi.android.handler;

import java.util.Collection;

import com.hixi_hyi.android.IdumoInterface;
import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.eventlistener.HandlerListener;
import com.hixi_hyi.android.eventlistener.ProviderListener;

/**
 * @author Hiroyoshi HOUCHI
 *
 */
public interface DataHandlerInterface extends IdumoInterface ,ProviderListener, HandlerListener {
	/**
	 * データの送り先を追加するメソッド
	 * @param l
	 */
	public void addHandlerListener(HandlerListener handlerListener);

	/**
	 * パラメータを設定するメソッド
	 * @param parameter
	 */
	public void setParameter(PipeData parameter);

	/**
	 * 指定されたパラメータがこのHandlerに設定できるものか調べるメソッド
	 * @param parameter
	 * @return
	 */
	public boolean isAccesibleParameter(PipeData parameter);


	/**
	 * 通知するデータのタイプを取得できるメソッド
	 * Integerが送られるとかStringが送られるとかわかる
	 * @return
	 */
	public Collection<Class<?>> getNotifyDataType();

}
