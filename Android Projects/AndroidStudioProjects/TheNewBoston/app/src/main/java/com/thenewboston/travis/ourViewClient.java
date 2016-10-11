package com.thenewboston.travis;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by satvik on 1/8/15.
 */
public class ourViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView v,String url){
        v.loadUrl(url);
        return true;
    }

}
