package com.example.autofillversion1official;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;

public class GoOnlineThree extends AppCompatActivity {
    private static final String desktop_mode = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36";


    private WebView webView;
    String employeeID;
    String temperature;
    String webUrl = "done";
    String url = "https://zh.surveymonkey.com/r/EmployeeHealthCheck";
    String doneUrl = "https://zh.surveymonkey.com/r/HCCompleted";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_layout);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        temperature = "36";
        employeeID = sharedPreferences.getString("ID3", "");

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        // Initializing the calendar Object for today's date
        Calendar cal = Calendar.getInstance();
        //using sdf to format to mm/dd/yyyy
        String curr_date = sdf.format(cal.getTime());

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

                webView.loadUrl("javascript:(function() { document.getElementById('87960815_688357155_label').click(); " +
                        "document.getElementById('87960813').value = '" + employeeID + "'; " +
                        "document.getElementById('87960820_688357202_label').click(); " +
                        //"document.getElementById('66405065').value = '" + temperature + "';" +
                        "document.getElementById('87960821_688357186_label').click();" +
                        "document.getElementById('87960822_688357192_DMY').value = '" + curr_date + "';" +
                        "document.getElementById('87960814_688357154_label').click();" +
                        "document.getElementsByClassName('btn small next-button survey-page-button user-generated notranslate')[0].click(); ;})()");
                webUrl = webView.getUrl();

                if (webUrl.equals(doneUrl)) {

                    Intent h = new Intent(GoOnlineThree.this, MainActivity.class);
                    startActivity(h);
                    finish();
                    WakeLocker.release();
                }

            }
        });

    }

}
