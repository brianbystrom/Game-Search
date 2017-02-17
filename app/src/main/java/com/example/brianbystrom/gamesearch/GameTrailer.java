package com.example.brianbystrom.gamesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class GameTrailer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_trailer);
        WebView webview = (WebView) findViewById(R.id.game_trailer);
        webview.getSettings().setJavaScriptEnabled(true);
        //setContentView(webview);

        if(getIntent().getExtras() != null) {
            String url = (String) getIntent().getExtras().getString("TRAILER_URL");

            url = url.replaceFirst("^(http://www\\.|http://|www\\.)","");
            Log.d("DEMO", url);
            webview.loadUrl("https://www." + url);


        }



    }
}
