package com.hixi_hyi.idumo.android.test;

import android.app.Activity;
import android.os.Bundle;

public class TextView extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.widget.TextView view = new android.widget.TextView(this);
		setContentView(view);
		view.setText("aaa");
	}
	
}
