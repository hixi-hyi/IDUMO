package com.hixi_hyi.android.receiptor.imp;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.receiptor.DataReceiptorInterface;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class TextViewReceiptor extends TextView implements
		DataReceiptorInterface {

	public TextViewReceiptor(Context context) {
		super(context);
		Activity activity = (Activity) context;
		activity.setContentView(this);
	}

	@Override
	public void handlerChanged(HandlerEvent e) {
		StringBuilder sb = new StringBuilder();
		for (Object o : e.getPipeData()) {
			sb.append(o.toString());
		}
		setText(sb.toString());
	}

	@Override
	public void setParameter(PipeData parameter) {
	}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		return false;
	}

	@Override
	public boolean isRegist(DataHandlerInterface handlerInterface) {
		return true;
	}

	@Override
	public void onStart() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onRestart() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onResume() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onPause() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStop() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
