package com.hixi_hyi.idumo.android.sample.google;

import java.util.ArrayList;

import com.hixi_hyi.idumo.android.ApplicationControlforAndroid;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.receiptor.TextViewReceiptor;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;

public class GDocs2View extends ListActivity implements Runnable {

	private ArrayList<ApplicationControlforAndroid> android;
	private GoogleDocs document;
	private ThroughHandler through;
	private TextViewReceiptor textView;
	private Thread thread;
	private boolean isDo;
	private Handler handler;

	@Override
	public void run() {
//		while(isDo){
//			LogUtil.d();
//			handler.post(textView);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO 自動生成された catch ブロック
//				e.printStackTrace();
//			}
//
//		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<ApplicationControlforAndroid>();
		handler = new Handler();


		document = new GoogleDocs(this);
		android.add(document);

//		through = new ThroughHandler();
//
//		textView = new TextViewReceiptor(this);
//
//
//		if(!textView.setSender(through)){
//			throw new RuntimeException();
//		}
//		if(!through.setSender(document)){
//			throw new RuntimeException();
//		}
//
//		document.setMethodType(AccelerometerProvider.Type.X);

	}

	@Override
	public void onStart() {
		super.onStart();
		for(ApplicationControlforAndroid a:android){
			a.onStart();
		}
	}

	@Override
	public void onRestart() {
		for(ApplicationControlforAndroid a:android){
			a.onRestart();
		}
		super.onRestart();
	}

	@Override
	public void onResume() {
		super.onResume();
		for(ApplicationControlforAndroid a:android){
			a.onResume();
		}
//		isDo = true;
//		thread = new Thread(this);
//		thread.start();
	}

	@Override
	public void onPause() {
		for(ApplicationControlforAndroid a:android){
			a.onPause();
		}
//		isDo = false;
		super.onPause();
	}

	@Override
	public void onStop() {
		for(ApplicationControlforAndroid a:android){
			a.onStop();
		}
		super.onStop();
	}

	@Override
	public void onDestroy() {
		for(ApplicationControlforAndroid a:android){
			a.onDestroy();
		}
		super.onDestroy();
	}

}