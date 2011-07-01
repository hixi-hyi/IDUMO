package com.hixi_hyi.android.receiptor.imp;

import java.util.ArrayList;

import com.hixi_hyi.android.data.PipeData;
import com.hixi_hyi.android.event.HandlerEvent;
import com.hixi_hyi.android.handler.DataHandlerInterface;
import com.hixi_hyi.android.receiptor.DataReceiptorInterface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class ImageViewReceiptor extends ImageView implements
		DataReceiptorInterface {

	public ImageViewReceiptor(Context context) {
		super(context);
		Activity activity = (Activity) context;
		activity.setContentView(this);
	}

	@Override
	public void handlerChanged(HandlerEvent e) {
		PipeData p = e.getPipeData();
		Bitmap image = (Bitmap) p.get(0);
		setImageBitmap(image);
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
		ArrayList<Class<?>> types = new ArrayList<Class<?>>(handlerInterface.getNotifyDataType());
		if(types.size()==1 && types.get(0)==Bitmap.class){
			return true;
		}
		return false;
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
