package com.hixi_hyi.android.receiptor.imp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.receiptor.DataReceiptorInterface;

public class TextReceiptor extends View implements DataReceiptorInterface {
	Activity activity;
	Paint p;
	private int x;
	private int y;
	String text;

	public TextReceiptor(Context context) {
		super(context);
		activity = (Activity) context;
		p = new Paint();
		x = 0;
		y = 0;
		text = "";
		setBackgroundColor(Color.WHITE);
		p.setTextSize(5.0f);
		activity.setContentView(this);
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawText(text, x, y, p);
	}

	@Override
	public void handlerChanged(HandlerEvent e) {
		text = e.getPipeData().toString();
		invalidate();
		Log.v("Receiptor", e.getClass().getSimpleName());
	}

	@Override
	public void setParameter(PipeData parameter) {
		x = (Integer) parameter.get(0);
		y = (Integer) parameter.get(1);
		p.setTextSize((Float) parameter.get(2));
	}

	@Override
	public boolean isAccesibleParameter(PipeData parameter) {
		return parameter.getDataType().equals(getParameterType());
	}

	private String getParameterType() {
		return Integer.class.getName() + "," + Integer.class.getName() + ","
				+ Float.class.getName();
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
