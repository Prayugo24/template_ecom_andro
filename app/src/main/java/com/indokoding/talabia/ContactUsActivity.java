package com.indokoding.talabia;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactUsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.webContactUs)
    WebView webContctUs;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    WebSettings webSettings;
    String URL = BuildConfig.URL_CONTACTUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        webSettings = webContctUs.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.getUseWideViewPort();

        webContctUs.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                swipeRefreshLayout.setRefreshing(true);
            }
        });

        webContctUs.setWebViewClient(new WebViewClient() {


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        webContctUs.loadUrl(URL);

    }

    @Override
    public void onRefresh() {

        webContctUs.reload();
    }

    @Override
    public void onBackPressed() {

        if (webContctUs.canGoBack()) {
            webContctUs.goBack();
        } else {
            finish();
            System.exit(0);
        }
    }
}
