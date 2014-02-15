package com.example.sessionreplay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

/**
 * Created by mhan on 2014-02-14.
 */
public class WebviewMaskingFragment extends WebViewFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        getWebView().setWebViewClient(new WebViewClient());
        getWebView().loadUrl("http://www.google.com");

        return root;
    }
}