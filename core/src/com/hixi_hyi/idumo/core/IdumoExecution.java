package com.hixi_hyi.idumo.core;

public interface IdumoExecution {
	public void onIdumoBuild() throws IdumoException;
	
	public void onIdumoStart();
	
	public void onIdumoStop();
	
	public void onIdumoExec() throws IdumoRuntimeException;
}
