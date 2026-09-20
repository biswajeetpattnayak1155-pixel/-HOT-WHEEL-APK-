package com.hotwheel.selfdrive;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.graphics.Color;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView splash = new ImageView(this);
        splash.setImageResource(R.drawable.hot_wheel_splash);
        splash.setScaleType(ImageView.ScaleType.CENTER_CROP);
        splash.setBackgroundColor(Color.BLACK);

        setContentView(splash);

        handler.postDelayed(() -> {

            WebView web = new WebView(this);
            web.setBackgroundColor(Color.BLACK);

            WebSettings settings = web.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);

            web.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    openExternal(url);
                    return true;
                }

                @Override
                public boolean shouldOverrideUrlLoading(
                        WebView view,
                        android.webkit.WebResourceRequest request) {
                    openExternal(request.getUrl().toString());
                    return true;
                }
            });

            web.setDownloadListener((url, userAgent, contentDisposition, mimeType, contentLength) -> {
                openExternal(url);
            });

            setContentView(web);

            web.loadUrl("file:///android_asset/index.html");

        }, 1600);
    }

    private void openExternal(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            try {
                Intent browser = new Intent(Intent.ACTION_VIEW);
                browser.setData(Uri.parse(url));
                startActivity(browser);
            } catch (Exception ignored) {
            }
        }
    }
}
