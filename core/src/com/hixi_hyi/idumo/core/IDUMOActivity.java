package com.hixi_hyi.idumo.core;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exception.IDUMORuntimeException;

public interface IDUMOActivity {
	public void onIdumoCreated() throws IDUMOException;
	
	public void onIdumoStart();
	
	public void onIdumoExec() throws IDUMORuntimeException;
	
	public void onIdumoStop();
	
}
