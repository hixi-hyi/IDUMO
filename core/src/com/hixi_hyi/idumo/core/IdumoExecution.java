package com.hixi_hyi.idumo.core;

public interface IdumoExecution {
	public void onIdumoCreated() throws IdumoException;
	
	public void onIdumoStart();
	
	public void onIdumoExec() throws IdumoRuntimeException;
	
	public void onIdumoStop();
	
}
