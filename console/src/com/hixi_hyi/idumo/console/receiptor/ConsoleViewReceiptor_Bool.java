package com.hixi_hyi.idumo.console.receiptor;

import java.util.ArrayList;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * Systemoutに出力するReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class ConsoleViewReceiptor_Bool implements Receiver, IdumoRunnable {
	
	private Sender	sender;
	private ReceiveValidatorType vType = new ReceiveValidatorType(1,Boolean.class);
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	
	@Override
	public void run() {
		if(!sender.isReady()){
			return;
		}
		if((Boolean)sender.getData().get(0)){
			System.out.println("OK");
		}else{
			System.out.println("NG");
		}
		
	}
	
	@Override
	public boolean setSender(Sender... handler) throws IdumoException {
		vType.validate(handler);
		vSize.validate(handler);
		sender = handler[0];
		return true;
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
}
