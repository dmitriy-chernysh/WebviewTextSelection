package com.mobiledevpro.webviewtextselection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initWebView();

    }

    private void initWebView() {
        mWebView.loadUrl("http://www.mobile-dev.pro/webview_text_selection.html");

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
