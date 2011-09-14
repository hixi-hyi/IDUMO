package com.hixi_hyi.idumo.android.receiptor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.util.LogManager;

/**
 * Android上にテキスト情報を出力するReceiptorです
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class TextViewReceiptor extends TextView implements Receiver, IdumoRunnable {
	
	private ArrayList<Sender>	senders;
	private Activity			activity;
	
	public TextViewReceiptor(Context context) {
		super(context);
		senders = new ArrayList<Sender>();
		activity = (Activity) context;
		activity.setContentView(this);
		setTextSize(30.0f);
	}
	
	@Override
	public void run() {
		LogManager.log();
		for (Sender sender : senders) {
			if (!sender.isReady()) {
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Sender sender : senders) {
			for (Object o : sender.getData()) {
				sb.append(o.toString());
			}
			sb.append("\n");
		}
		
		LogManager.debug(sb.toString());
		
		setText(sb.toString());
		
	}
	
	@Override
	public boolean setSender(Sender... handler) {
		senders.clear();
		for (Sender s : handler) {
			senders.add(s);
		}
		return true;
	}
	
	@Override
	public boolean isReady() {
		if (senders.size() == 0) {
			return false;
		}
		for (Sender sender : senders) {
			if (!sender.isReady()) {
				return false;
			}
		}
		return true;
	}
	
}
