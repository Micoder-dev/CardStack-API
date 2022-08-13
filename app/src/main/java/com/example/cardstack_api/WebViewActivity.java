package com.example.cardstack_api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        mWebView = findViewById(R.id.webview);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        progressBar = ProgressDialog.show(WebViewActivity.this, "Please wait...", "your page is loading...");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }
        });
        mWebView.loadUrl(url);

    }
}