package com.keepmeposted.views.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.keepmeposted.R;

/**
 * Created by chamal on 11/4/2016.
 */

public class URLActivity extends Activity{

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_web);

    WebView webView = (WebView)findViewById(R.id.webViewRecipe);

    webView.setInitialScale(1);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setLoadWithOverviewMode(true);
    webView.getSettings().setUseWideViewPort(true);
    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    webView.setScrollbarFadingEnabled(false);

    webView.loadUrl("http://www.epicurious.com/");
    }
}
