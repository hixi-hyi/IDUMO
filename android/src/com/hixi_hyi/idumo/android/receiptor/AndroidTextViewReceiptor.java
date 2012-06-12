/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.hixi_hyi.idumo.android.receiptor;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.hixi_hyi.idumo.core.data.Data;
import com.hixi_hyi.idumo.core.data.FlowingData;
import com.hixi_hyi.idumo.core.data.connect.ArrayConnectDataType;
import com.hixi_hyi.idumo.core.data.connect.ConnectDataType;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.Executable;
import com.hixi_hyi.idumo.core.parts.Receivable;
import com.hixi_hyi.idumo.core.parts.Sendable;
import com.hixi_hyi.idumo.core.util.LogManager;
import com.hixi_hyi.idumo.core.validator.ReceiveValidatorSize;

/**
 * Android上にテキスト情報を出力するReceiptorです
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
public class AndroidTextViewReceiptor extends TextView implements Receivable, Executable {
	
	private Sendable				sender;
	private ReceiveValidatorSize	vSize	= new ReceiveValidatorSize(1);
	private Activity				activity;
	
	public AndroidTextViewReceiptor(Context context) {
		super(context);
		activity = (Activity) context;
		activity.setContentView(this);
		setTextSize(30.0f);
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(Data.class);
	}
	
	@Override
	public void run() {
		LogManager.log();
		FlowingData idf = sender.onCall();
		StringBuilder sb = new StringBuilder();
		for (Data d : idf) {
			sb.append(d.toString());
		}
		
		LogManager.debug(sb.toString());
		
		setText(sb.toString());
		
	}
	
	@Override
	public void setSender(Sendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}
	
}
