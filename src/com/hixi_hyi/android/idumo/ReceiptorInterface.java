package com.hixi_hyi.android.idumo;

import java.util.Collection;

import com.hixi_hyi.android.idumo.data.PipeData;
import com.hixi_hyi.android.idumo.eventlistener.HandlerListener;

public interface ReceiptorInterface extends Runnable{
	public void setParameter(PipeData parameter);
	public boolean isAccesibleParameter(PipeData parameter);
	public boolean addHandler(HandlerInterface handler);
}
