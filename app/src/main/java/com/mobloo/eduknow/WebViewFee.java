package com.mobloo.eduknow;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class WebViewFee extends AppCompatActivity
{
    private WebView feeLoader;
    private static final String URL = "https://pay.eduknow.info/:recipt_id/:number";
    ProgressDialog prDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_fee);
        feeLoader = (WebView)findViewById(R.id.web_loader);
        feeLoader.setWebViewClient(new MyBrowser() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
            {
                Toast.makeText(getApplicationContext(), "Please check your Data Connection", Toast.LENGTH_LONG).show();
                finish();


            }
        });
        feeLoader.getSettings().setDomStorageEnabled(true);
        feeLoader.getSettings().setLoadsImagesAutomatically(true);
        feeLoader.getSettings().setAllowFileAccess(true);
        feeLoader.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        feeLoader.getSettings().setLoadsImagesAutomatically(true);
        feeLoader.getSettings().setJavaScriptEnabled(true);
        feeLoader.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        feeLoader.loadUrl(URL);
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            prDialog = new ProgressDialog(WebViewFee.this);
            prDialog.setMessage("Please wait ...");
            prDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (prDialog != null) {
                prDialog.dismiss();
            }
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to cancel the transaction?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            WebViewFee.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
