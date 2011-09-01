
package com.hixi_hyi.idumo.console.util;

import com.hixi_hyi.idumo.core.util.Logger;

public class ConsoleLogger implements Logger {

	private static final int NUMBER = 4;

	public String getFileLineMethod(){
		int number = Thread.currentThread().getStackTrace()[NUMBER].getLineNumber();
		String classname = Thread.currentThread().getStackTrace()[NUMBER].getFileName();
		String methodname = Thread.currentThread().getStackTrace()[NUMBER].getMethodName();
		return String.format("【%3d:%s->%s】", number,classname,methodname);
	}

	@Override
	public void debug(String s) {
		System.err.println(String.format("%-10s %s",s,getFileLineMethod()));
	}

	@Override
	public void info(String s) {
		System.err.println(String.format("%-10s %s",s,getFileLineMethod()));
	}

	@Override
	public void warning(String s) {
		System.err.println(String.format("%-10s %s",s,getFileLineMethod()));
	}

	@Override
	public void log() {
		System.err.println(getFileLineMethod());
	}

}
