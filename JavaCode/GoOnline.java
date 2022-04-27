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

public class GoOnline extends AppCompatActivity {
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
        //for accessing phone data that user saved for future
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        //website wants us to enter date in mm/dd/yyyy, need to set up date format with sdf
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        // Initializing the calendar Object for today's date
        Calendar cal = Calendar.getInstance();
        //using sdf to format to mm/dd/yyyy
        String curr_date = sdf.format(cal.getTime());
        temperature = "36";
        employeeID = sharedPreferences.getString("ID1", "");

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
                    webView.loadUrl("javascript:(function() { document.getElementById('66405067_542650090').click(); " +
                            "document.getElementById('66405064').value = '" + employeeID + "'; " +
                            "document.getElementById('66405069_542650094').click(); " +
                            "document.getElementById('66405065').value = '" + temperature + "';" +
                            "document.getElementById('66405075_542650132').click();" +
                            "document.getElementById('66405078_542650162').click();" +
                            "document.getElementById('66405129_542650744_DMY').value = '" + curr_date + "';" +
                            "document.getElementById('66405074_542650161').click();" +
                            "document.getElementById('66405076_542650156').click();" +
                            "document.getElementById('66405066_542650082').click();" +
                            "document.getElementsByClassName('btn small next-button survey-page-button user-generated notranslate')[0].click(); ;})()");

                webUrl = webView.getUrl();

                if (webUrl.equals(doneUrl)){
                    Intent h = new Intent(GoOnline.this, GoOnlineTwo.class);
                    h.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startActivity(h);
                    }
                }

            }
        });

    }


    public void setDesktopMode(final boolean enabled) {
        final WebSettings webSettings = webView.getSettings();

        final String newUserAgent;
        if (enabled) {
            newUserAgent = webSettings.getUserAgentString().replace("Mobile", "eliboM").replace("Android", "diordnA");
        }
        else {
            newUserAgent = webSettings.getUserAgentString().replace("eliboM", "Mobile").replace("diordnA", "Android");
        }

        webSettings.setUserAgentString(newUserAgent);
        webSettings.setUseWideViewPort(enabled);
        webSettings.setLoadWithOverviewMode(enabled);
        webSettings.setSupportZoom(enabled);
        webSettings.setBuiltInZoomControls(enabled);
    }
}




