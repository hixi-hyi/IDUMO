package jp.idumo.android.core.exec;

import java.util.Collection;

import jp.idumo.android.core.AndroidController;
import jp.idumo.core.exec.CoreComponent;

import android.app.Activity;


public abstract class AndroidComponent extends CoreComponent {
	
	protected Activity	activity;
	
	// public Activity getActivity() {
	// return activity;
	// }
	
	public Collection<AndroidController> getAndroidControllers() {
		AndroidContainer container = (AndroidContainer) getContainer();
		return container.getAndroidControllers();
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
}
