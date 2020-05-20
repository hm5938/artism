package com.example.art2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private WebView mWebView;
    int index = 0;
    private long lastTimeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.artbrutkor.com/");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClientClass());
    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Toast.makeText(this, "세로모드", Toast.LENGTH_SHORT).show();
//        }
//
//        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "가로모드", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }



    // 백버튼 터치시 웹뷰페이지 뒤로 가기. 더이상 뒤로 갈곳이 없으면 연속 두번 터치시 종료
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();

        } else {
            if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
                finish();
                return;
            }
            lastTimeBackPressed = System.currentTimeMillis();
            Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }


    private class WebViewClientClass extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            Log.d("check URL", url);
            view.loadUrl(url);
            return true;
        }
    }
}
