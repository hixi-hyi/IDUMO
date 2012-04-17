package com.hixi_hyi.idumo.android.receiptor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.hixi_hyi.idumo.core.parts.IDUMOReceiver;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSender;
import com.hixi_hyi.idumo.core.util.IDUMOLogManager;

/**
 * Android上にテキスト情報を出力するReceiptorです
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class TextViewReceiptor extends TextView implements IDUMOReceiver, IDUMORunnable {
	
	private ArrayList<IDUMOSender>	senders;
	private Activity			activity;
	
	public TextViewReceiptor(Context context) {
		super(context);
		senders = new ArrayList<IDUMOSender>();
		activity = (Activity) context;
		activity.setContentView(this);
		setTextSize(30.0f);
	}
	
	@Override
	public void run() {
		IDUMOLogManager.log();
		for (IDUMOSender sender : senders) {
			if (!sender.isReady()) {
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (IDUMOSender sender : senders) {
			if(sender.get()==null){
				return;
			}
			for (Object o : sender.get()) {
				IDUMOLogManager.debug(o);
				sb.append(o.toString());
			}
			sb.append("\n");
		}
		
		IDUMOLogManager.debug(sb.toString());
		
		setText(sb.toString());
		
	}
	
	@Override
	public boolean setSender(IDUMOSender... handler) {
		senders.clear();
		for (IDUMOSender s : handler) {
			senders.add(s);
		}
		return true;
	}
	
	@Override
	public boolean isReady() {
		if (senders.size() == 0) {
			return false;
		}
		for (IDUMOSender sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}
	
}
