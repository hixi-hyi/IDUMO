package com.hixi_hyi.idumo.console.receiptor;

import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorType;

/**
 * Systemoutに出力するReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class ConsoleViewReceiptor_Bool implements IDUMOReceiver, IDUMORunnable {
	
	private IDUMOSender	sender;
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
	public boolean setSender(IDUMOSender... handler) throws IDUMOException {
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
