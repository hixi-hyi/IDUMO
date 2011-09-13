package com.hixi_hyi.idumo.android.sample.google;

import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.GoogleUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.sample.docs.v3.model.Feed;
import com.google.api.client.sample.docs.v3.model.Link;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.atom.AtomParser;
import com.hixi_hyi.idumo.android.ApplicationControllerForAndroid;
import com.hixi_hyi.idumo.android.util.AndroidLogger;
import com.hixi_hyi.idumo.core.util.LogManager;

public class GoogleDocs implements ApplicationControllerForAndroid{


	public GoogleDocs(Activity activity){
		// AccountManager を通じてGoogleアカウントを取得
		AccountManager manager = AccountManager.get(activity);
		Account[] accounts =
			manager.getAccountsByType("com.google");
		Bundle bundle = null;
		try {
			bundle = manager.getAuthToken(
					accounts[0], // テストなので固定
					"writely",   // ※1
					null,
					activity,
					null,
					null).getResult();
		} catch (OperationCanceledException e) {
			LogManager.debug(e);
		} catch (AuthenticatorException e) {
			LogManager.debug(e);
		} catch (IOException e) {
			LogManager.debug(e);
		}

		String authToken = "";
		if (bundle.containsKey(AccountManager.KEY_INTENT)) {
			// 認証が必要な場合
			Intent intent = bundle.getParcelable(AccountManager.KEY_INTENT);
			int flags = intent.getFlags();
			flags &= ~Intent.FLAG_ACTIVITY_NEW_TASK;
			intent.setFlags(flags);
			activity.startActivityForResult(intent, 0);
			// 本当はResultを受けとる必要があるけど割愛
			return;
		} else {
			// 認証用トークン取得
			authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
		}

		// 送信準備
//		HttpTransport transport = GoogleTransport.create();
		HttpTransport transport = AndroidHttp.newCompatibleTransport();
		GoogleHeaders headers = (GoogleHeaders) transport.defaultHeaders;
		headers.setApplicationName("Kokufu-GoogleDocsTest/1.0");
		headers.gdataVersion = "3";
		headers.setGoogleLogin(authToken); // 認証トークン設定

		// Parser を準備して Transport にセットする
		AtomParser parser = new AtomParser();
		// 空の Dictionary でとりあえず問題なさげ
		parser.namespaceDictionary =
			new XmlNamespaceDictionary();
		transport.addParser(parser);

		// 送信
		Feed feed = null;
		try {
			HttpRequest request = transport.buildGetRequest();
			request.url = new GoogleUrl("https://docs.google.com/feeds/default/private/full"); // ※2
			feed = request.execute().parseAs(Feed.class);
		} catch (IOException e) {
			LogManager.debug(e);
		}

		// 結果を表示
		String tmp = "";
		for (Link link : feed.links) {
			LogManager.debug(link);
		}
//		TextView v = new TextView(this);
//		v.setText(tmp);
//		this.addContentView(
//				v,
//				new LayoutParams(LayoutParams.WRAP_CONTENT,
//						LayoutParams.WRAP_CONTENT));
	}





	@Override
	public void onIdumoStart() {
	}

	@Override
	public void onIdumoRestart() {
	}

	@Override
	public void onIdumoResume() {
	}

	@Override
	public void onIdumoPause() {
	}

	@Override
	public void onIdumoStop() {
	}

	@Override
	public void onIdumoDestroy() {
	}

}
