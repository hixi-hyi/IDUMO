package com.hixi_hyi.idumo.core;

/**
 * Idumoの構成要素(ブロック図の要素)になるインタフェースです．
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface IdumoComponent {
	/**
	 * 構成要素が呼び出される準備ができているかを返す
	 * 
	 * @return
	 */
	public boolean isReady();
}
