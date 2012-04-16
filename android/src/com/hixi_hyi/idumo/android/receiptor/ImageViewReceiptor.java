package com.hixi_hyi.idumo.android.receiptor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.IdumoRunnable;
import com.hixi_hyi.idumo.core.Receiver;
import com.hixi_hyi.idumo.core.Sender;
import com.hixi_hyi.idumo.core.data.PipeData;

// TODO 非検証
/**
 * 画像を表示するためのクラス
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class ImageViewReceiptor extends ImageView implements Receiver, IdumoRunnable {

	private Sender		sender;
	private Activity	activity;

	public ImageViewReceiptor(Context context) {
		super(context);
		activity = (Activity) context;
		activity.setContentView(this);
	}

	@Override
	public void run() {
		PipeData p = sender.getData();
		Bitmap image = (Bitmap) p.get(0);
		setImageBitmap(image);
	}

	@Override
	public boolean setSender(Sender... senders) throws IdumoException {
			Sender sender = senders[0];
			ArrayList<Class<?>> list = new ArrayList<Class<?>>(sender.getDataType());
			if (list.get(0) == Bitmap.class) {
				return true;
			}
		return false;
	}

	@Override
	public boolean isReady() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
