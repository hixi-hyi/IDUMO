package com.hixi_hyi.idumo.android.util;

import android.util.Log;

public class LogUtil {
	public static boolean isDebug;
	public static String TAG;

	public static final void d(Object msg) {
		if (isDebug) {
			Log.d(TAG, String.format("%-10s %s",msg.toString(),GetFileLineMethod()));
		}
	}
	public static final void d() {
		if (isDebug) {
			Log.d(TAG, GetFileLineMethod());
		}
	}
	public static String GetFileLineMethod(){
		int number = Thread.currentThread().getStackTrace()[4].getLineNumber();
		String classname = Thread.currentThread().getStackTrace()[4].getFileName();
		String methodname = Thread.currentThread().getStackTrace()[4].getMethodName();
		return String.format("【%3d:%s->%s】", number,classname,methodname);
	}
}
