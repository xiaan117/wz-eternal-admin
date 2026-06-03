package com.wzadmin;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView webView;
    private static final String ADMIN_URL = "https://remote-control-y0w1.onrender.com/admin";
    private static final String ADMIN_PASSWORD = "zhang!117@621621jj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String js = "javascript:(function(){" +
                        "var pwd = document.getElementById('pwdInput');" +
                        "if(pwd) {" +
                        "  pwd.value = '" + ADMIN_PASSWORD + "';" +
                        "  var btn = document.querySelector('button');" +
                        "  if(btn && btn.innerText.indexOf('进入后台') !== -1) {" +
                        "    btn.click();" +
                        "  }" +
                        "}" +
                        "})()";
                view.loadUrl(js);
            }
        });

        webView.loadUrl(ADMIN_URL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
