package com.example.citybang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewSearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_search);

        WebView webView = findViewById(R.id.wv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new BridgeInterface(), "Android");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // 안드로이드에서 자바스크립트 함수 호출
                webView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        //최초 웹뷰 로드
        webView.loadUrl("https://searchaddre.web.app");
    }

    private class BridgeInterface {
        @JavascriptInterface
        public void processDATA(String data){
            // 카카오 주소 검색  API의 결과 값이 브릿지 통로를 통해 전달 받는다(Javascript)
            Intent intent = new Intent();
            intent.putExtra("data",data);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}