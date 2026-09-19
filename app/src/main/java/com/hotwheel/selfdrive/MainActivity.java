package com.hotwheel.selfdrive;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.graphics.Color;
import android.view.View;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView web = new WebView(this);
        web.setBackgroundColor(Color.BLACK);
        web.setWebViewClient(new WebViewClient());
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);

        web.setDownloadListener((url, userAgent, contentDisposition, mimeType, contentLength) -> {
            try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); } catch (Exception ignored) {}
        });

        setContentView(web);
        web.loadUrl("file:///android_asset/index.html");
    }
}
