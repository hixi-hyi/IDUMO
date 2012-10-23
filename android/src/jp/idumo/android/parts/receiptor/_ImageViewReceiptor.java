package jp.idumo.android.parts.receiptor;

import jp.idumo.core.data.FlowingData;
import jp.idumo.core.data.connect.ConnectDataType;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.parts.Executable;
import jp.idumo.core.parts.Receivable;
import jp.idumo.core.parts.Sendable;
import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;


// TODO 非検証
/**
 * 画像を表示するためのクラス
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _ImageViewReceiptor extends ImageView implements Receivable, Executable {
	
	private Sendable	sender;
	private Activity	activity;
	
	public _ImageViewReceiptor(Context context) {
		super(context);
		activity = (Activity) context;
		activity.setContentView(this);
	}
	
	@Override
	public boolean isReady() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	@Override
	public ConnectDataType receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public void run() {
		FlowingData p = sender.onCall();
		// Bitmap image = (Bitmap) p.get(0);
		// setImageBitmap(image);
	}
	
	@Override
	public void setSender(Sendable... senders) throws IDUMOException {
		Sendable sender = senders[0];
		// ArrayList<Class<?>> list = new
		// ArrayList<Class<?>>(sender.getDataType());
		// if (list.get(0) == Bitmap.class) {
		// return true;
		// }
	}
	
}
