package com.hixi_hyi.android.provider;

import java.util.Collection;

import android.hardware.SensorManager;

import com.hixi_hyi.android.IdumoInterface;
import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.eventlistener.ProviderListener;

/**
 * @author Hiroyoshi HOUCHI
 *
 */
public interface DataProviderInterface extends IdumoInterface{

	/**
	 * データの送り先を追加するメソッド
	 * @param l データの送り先(HandlerやReceiptor)
	 */
	public void addProviderListener(ProviderListener l);

	/**
	 * 使うセンサータイプを返すメソッド
	 * @return
	 */
//	public int useSensorType();

	/**
	 * 通知するデータのタイプを取得できるメソッド
	 * Integerが送られるとかStringが送られるとかわかる
	 * @return
	 */
	public Collection<Class<?>> getNotifyDataType();

}
