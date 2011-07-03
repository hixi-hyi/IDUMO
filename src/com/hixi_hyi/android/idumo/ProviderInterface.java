package com.hixi_hyi.android.idumo;

import java.util.Collection;

import com.hixi_hyi.android.idumo.data.PipeData;

/**
 * @author Hiroyoshi HOUCHI
 *
 */
public interface ProviderInterface{
	public PipeData getData();
	public boolean isReady();
	public Collection<Class<?>> getNotifyDataType();
	public void setParameter(PipeData parameter);
	public boolean isAccesibleParameter(PipeData parameter);
}
