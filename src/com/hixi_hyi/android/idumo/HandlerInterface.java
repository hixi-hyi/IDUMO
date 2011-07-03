package com.hixi_hyi.android.idumo;

import java.util.Collection;

import com.hixi_hyi.android.idumo.data.PipeData;

/**
 * @author Hiroyoshi HOUCHI
 *
 */
public interface HandlerInterface{
	public PipeData getData();
	public Collection<Class<?>> getNotifyDataType();
	public void setParameter(PipeData parameter);
	public boolean isAccesibleParameter(PipeData parameter);
	public boolean addProvider(ProviderInterface provider);
}
