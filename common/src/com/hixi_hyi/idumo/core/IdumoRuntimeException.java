package com.hixi_hyi.idumo.core;

public class IdumoRuntimeException extends RuntimeException {

	public IdumoRuntimeException() {
		super();
	}

	public IdumoRuntimeException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public IdumoRuntimeException(String detailMessage) {
		super(detailMessage);
	}

	public IdumoRuntimeException(Throwable throwable) {
		super(throwable);
	}

}
