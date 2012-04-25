package com.hixi_hyi.idumo.android.receiptor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上にテキスト情報を出力するReceiptorです
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _TextViewReceiptor extends TextView implements IDUMOReceivable, IDUMORunnable {
	
	private ArrayList<IDUMOSendable>	senders;
	private Activity					activity;
	
	public _TextViewReceiptor(Context context) {
		super(context);
		senders = new ArrayList<IDUMOSendable>();
		activity = (Activity) context;
		activity.setContentView(this);
		setTextSize(30.0f);
	}
	
	@Override
	public void run() {
		IDUMOLogManager.log();
		for (IDUMOSendable sender : senders) {
			if (!sender.isReady()) {
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (IDUMOSendable sender : senders) {
			if (sender.onCall() == null) {
				return;
			}
			for (Object o : sender.onCall()) {
				IDUMOLogManager.debug(o);
				sb.append(o.toString());
			}
			sb.append("\n");
		}
		
		IDUMOLogManager.debug(sb.toString());
		
		setText(sb.toString());
		
	}
	
	@Override
	public void setSender(IDUMOSendable... handler) {
		senders.clear();
		for (IDUMOSendable s : handler) {
			senders.add(s);
		}
		
	}
	
	@Override
	public boolean isReady() {
		if (senders.size() == 0) {
			return false;
		}
		for (IDUMOSendable sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public IDUMODataTypeConnect receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
