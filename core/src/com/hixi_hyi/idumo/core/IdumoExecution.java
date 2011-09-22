package com.hixi_hyi.idumo.core;

public interface IdumoExecution {
	public void onIdumoMakeFlowChart() throws IdumoException;

	public void onIdumoPrepare();

	public void onIdumoStart();

	public void onIdumoStop();

	public void onIdumoExec() throws IdumoRuntimeException;
}
