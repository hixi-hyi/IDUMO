package com.hixi_hyi.idumo.core.exception;

public class IDUMORuntimeException extends RuntimeException {
	
	public IDUMORuntimeException() {
		super();
	}
	
	public IDUMORuntimeException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
	
	public IDUMORuntimeException(String detailMessage) {
		super(detailMessage);
	}
	
	public IDUMORuntimeException(Throwable throwable) {
		super(throwable);
	}
	
}
