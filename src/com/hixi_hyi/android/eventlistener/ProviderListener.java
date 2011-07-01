package com.hixi_hyi.android.eventlistener;

import com.hixi_hyi.android.event.ProviderEvent;
import com.hixi_hyi.android.provider.DataProviderInterface;

public interface ProviderListener extends IdumoListener {
	public void providerChanged(ProviderEvent event);
	public boolean isRegist(DataProviderInterface providerInterface);
}
