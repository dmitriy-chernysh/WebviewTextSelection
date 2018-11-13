package com.mobiledevpro.webviewtextselection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mobiledevpro.webtexthighlight.WebTextHighlightHelper;

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

    @Override
    public void onActionModeStarted(ActionMode mode) {
        WebTextHighlightHelper.onActivityActionMenuStarted(this, mode);
        super.onActionModeStarted(mode);
    }


    public void onContextualMenuItemClicked(MenuItem item) {
        WebTextHighlightHelper.onContextualMenuItemClicked(mWebView, item, html -> {
            //do something with html
        });
    }


    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("http://www.mobile-dev.pro/webview_text_selection.html");
    }
}
