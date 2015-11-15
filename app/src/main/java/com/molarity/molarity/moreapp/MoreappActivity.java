package com.molarity.molarity.moreapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.http.SslError;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.molarity.molarity.R;

import org.w3c.dom.Text;

public class MoreappActivity extends Activity {

    private WebView webView;
    private TextView backButton;
    private ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreapp);

        backButton = (TextView) findViewById(R.id.backToDashboard);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView = (WebView) findViewById(R.id.webView);

        progDailog = ProgressDialog.show(this, "Loading","Please wait...", true);
        progDailog.setCancelable(false);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                Log.v("Molarity", description);
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                           SslError error) {
                Log.v("Molarity", error.toString());
                handler.cancel();
            }
        });

        webView.loadUrl("https://www.geneseesci.com/moreapps/moreapps.html");
    }
}
