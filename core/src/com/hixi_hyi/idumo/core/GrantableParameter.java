package com.hixi_hyi.idumo.core;

import com.hixi_hyi.idumo.core.data.PipeData;

/**
 * パラメータを受け取る必要のあるクラスに実装してください
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface GrantableParameter {
	public void setParameter(PipeData parameter);
	
	public boolean isAccesibleParameter(PipeData parameter);
}
