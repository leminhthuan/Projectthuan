package com.miosys.finder.web;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.miosys.finder.listview.PFinder_ListView;
import com.miosys.finder.ui.R;

public class PFinder_WebView extends Activity {

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pfinder_web_view);
		
		Button btnBack2 = (Button) findViewById(R.id.btnBack2);
		btnBack2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		Intent i = getIntent();
		String c = i.getStringExtra(PFinder_ListView.c);

		WebView browser = (WebView) findViewById(R.id.webView1);
				// Load the URLs inside the WebView, not in the external web browser
		browser.setWebViewClient(new WebViewClient());
		
		browser.loadUrl(c);
		browser.getSettings().setJavaScriptEnabled(true);
	}

}
