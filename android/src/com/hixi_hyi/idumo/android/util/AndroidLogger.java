package com.hixi_hyi.idumo.android.util;

import com.hixi_hyi.idumo.core.util.Logger;

import android.util.Log;

public class AndroidLogger implements Logger{
	private String tag;

	public AndroidLogger(String tag){
		this.tag = tag;
	}

	public String getFileLineMethod(){
		int number = Thread.currentThread().getStackTrace()[5].getLineNumber();
		String classname = Thread.currentThread().getStackTrace()[5].getFileName();
		String methodname = Thread.currentThread().getStackTrace()[5].getMethodName();
		return String.format("【%3d:%s->%s】", number,classname,methodname);
	}
	@Override
	public void debug(String s) {
		Log.d(tag, String.format("%-10s %s",s,getFileLineMethod()));
	}
	@Override
	public void info(String s) {
		Log.i(tag, String.format("%-10s %s",s,getFileLineMethod()));
	}
	@Override
	public void warning(String s) {
		Log.w(tag, String.format("%-10s %s",s,getFileLineMethod()));
	}

	@Override
	public void log() {
		Log.d(tag, getFileLineMethod());
	}
}
