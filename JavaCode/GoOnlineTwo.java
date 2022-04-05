package com.example.autofillversion1official;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;

public class GoOnlineTwo extends AppCompatActivity {
    private static final String desktop_mode = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36";


    private WebView webView;
    String employeeID;
    String temperature;
    String webUrl = "done";
    String url = "https://zh.surveymonkey.com/r/EmployeeHealthCheck";
    String doneUrl = "https://zh.surveymonkey.com/r/HCCompleted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_layout);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        temperature = "36";
        employeeID = sharedPreferences.getString("ID2", "");


        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        //setDesktopMode(true);
        //make external browser

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String url) {
                //webView.loadUrl("javascript:document.getElementById('763128014').value = '" + employeeID1 + "';");

                webView.loadUrl("javascript:(function() { document.getElementById('62391608_516129376').click(); " +
                        "document.getElementById('62391605').value = '" + employeeID + "'; " +
                        "document.getElementById('62391610_516129380').click(); " +
                        "document.getElementById('62391606').value = '" + temperature + "';" +
                        "document.getElementById('62391616_516129418').click();" +
                        "document.getElementById('62391615_516129447').click();" +
                        "document.getElementById('62391617_516129442').click();" +
                        "document.getElementById('62391607_516129368').click();" +
                        "document.getElementsByClassName('btn small next-button survey-page-button user-generated notranslate')[0].click(); ;})()");
                webUrl = webView.getUrl();

                if (webUrl.equals(doneUrl)) {
                    Intent h = new Intent(GoOnlineTwo.this, GoOnlineThree.class);
                    startActivity(h);
                }

            }
        });

    }

}
