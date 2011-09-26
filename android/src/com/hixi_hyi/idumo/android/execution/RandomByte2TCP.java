package com.hixi_hyi.idumo.android.execution;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.hixi_hyi.idumo.android.AndroidController;
import com.hixi_hyi.idumo.android.handler.ThroughHandler;
import com.hixi_hyi.idumo.android.receiptor.TCPByteStreamReceiptor;
import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.provider.RandomByteProvider;
import com.hixi_hyi.idumo.core.util.LogManager;

public class RandomByte2TCP extends Activity implements Runnable {
	
	// private static final String IP="172.21.67.142";
	private static final String				IP		= "192.168.12.10";
	private static final int				PORT	= 10000;
	
	private ArrayList<AndroidController>	android;
	private RandomByteProvider				ramdombyte;
	private ThroughHandler					through;
	private TCPByteStreamReceiptor			tcp;
	
	private Thread							thread;
	private boolean							isDo;
	
	@Override
	public void run() {
		while (isDo) {
			LogManager.log();
			tcp.run();
			// handler.post(tcp);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android = new ArrayList<AndroidController>();
		
		ramdombyte = new RandomByteProvider();
		
		through = new ThroughHandler();
		
		tcp = new TCPByteStreamReceiptor(IP, PORT);
		android.add(tcp);
		
		if (!through.setSender(ramdombyte)) {
			throw new RuntimeException();
		}
		try {
			tcp.setSender(through);
		} catch (IdumoException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		for (AndroidController a : android) {
			a.onIdumoStart();
		}
	}
	
	@Override
	public void onRestart() {
		for (AndroidController a : android) {
			a.onIdumoRestart();
		}
		super.onRestart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		for (AndroidController a : android) {
			a.onIdumoResume();
		}
		isDo = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void onPause() {
		for (AndroidController a : android) {
			a.onIdumoPause();
		}
		isDo = false;
		super.onPause();
	}
	
	@Override
	public void onStop() {
		for (AndroidController a : android) {
			a.onIdumoStop();
		}
		super.onStop();
	}
	
	@Override
	public void onDestroy() {
		for (AndroidController a : android) {
			a.onIdumoDestroy();
		}
		super.onDestroy();
	}
	
}
