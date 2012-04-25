package com.hixi_hyi.idumo.android.receiptor;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.hixi_hyi.idumo.core.data.IDUMOData;
import com.hixi_hyi.idumo.core.data.IDUMODataFlowing;
import com.hixi_hyi.idumo.core.data.connect.IDUMODataTypeConnect;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.parts.IDUMOReceivable;
import com.hixi_hyi.idumo.core.parts.IDUMORunnable;
import com.hixi_hyi.idumo.core.parts.IDUMOSendable;

// TODO 非検証
/**
 * 画像を表示するためのクラス
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class ImageViewReceiptor extends ImageView implements IDUMOReceivable, IDUMORunnable {
	
	private IDUMOSendable	sender;
	private Activity	activity;
	
	public ImageViewReceiptor(Context context) {
		super(context);
		activity = (Activity) context;
		activity.setContentView(this);
	}
	
	@Override
	public void run() {
		IDUMODataFlowing p = sender.onCall();
		// Bitmap image = (Bitmap) p.get(0);
		// setImageBitmap(image);
	}
	
	@Override
	public void setSender(IDUMOSendable... senders) throws IDUMOException {
		IDUMOSendable sender = senders[0];
		// ArrayList<Class<?>> list = new
		// ArrayList<Class<?>>(sender.getDataType());
		// if (list.get(0) == Bitmap.class) {
		// return true;
		// }
	}
	
	@Override
	public boolean isReady() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	@Override
	public IDUMODataTypeConnect receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
