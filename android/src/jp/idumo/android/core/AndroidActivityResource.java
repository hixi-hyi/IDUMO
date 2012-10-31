package jp.idumo.android.core;

import android.app.Activity;
import android.content.Context;

public class AndroidActivityResource {
	private Activity activity;
	private Context  applicationContext;
	
	public AndroidActivityResource(Activity activity){
		this.activity = activity;
		this.applicationContext = activity.getApplicationContext();
	}
	
	public Activity getActivity(){
		return activity;
	}
	
	public Context getApplicationContext(){
		return applicationContext;
	}
	
}
