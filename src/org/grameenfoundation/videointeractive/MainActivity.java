package org.grameenfoundation.videointeractive;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.*;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    public WebView webView;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            LayoutInflater inflator = getLayoutInflater();
            View mainView = inflator.inflate(R.layout.main, null);
            webView = (WebView) mainView.findViewById(R.id.webview);
            setContentView(mainView);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
            webView.getSettings().setAllowContentAccess(true);
            webView.getSettings().setBlockNetworkImage(false);
            webView.getSettings().setDomStorageEnabled(true);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    view.clearCache(true);
                }

                @Override
                public void onLoadResource(WebView view, String url) {
                    //super.onLoadResource(view, url);
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }
            });


            webView.setWebChromeClient(new WebChromeClient() {

            });

            String url = "file://" + Environment.getExternalStorageDirectory() + "/GFVIDEO/story_html5.html";
            //String url = "http://www.google.com";
            //String url = "http://beta.html5test.com/";
            webView.loadUrl(url);

        } catch (Exception ex) {
            Log.e(MainActivity.class.getName(), "Application Error", ex);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            if (item.getItemId() == R.id.action_refresh) {
                webView.reload();
            }
        } catch (Exception ex) {
            Log.e(MainActivity.class.getName(), "", ex);
        }

        return true;
    }
}

