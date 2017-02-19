/*
Assignment #: Homework 05
File Name: GameTrailer.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.gamesearch;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class GameTrailer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_trailer);
        WebView webview = (WebView) findViewById(R.id.game_trailer);
        TextView title = (TextView) findViewById(R.id.title_label);

        if(getIntent().getExtras() != null) {


            String url = (String) getIntent().getExtras().getString("TRAILER_URL");
            String titleString = (String) getIntent().getExtras().getString("GAME_TITLE");

            title.setText(titleString + " Trailer");
            url = url.replace("watch?v=", "embed/");
            String html = "";
            html += "<html><body>";
            html += "<iframe width=\"300\" height=\"168\" src=\""+ url + "\" frameborder=\"0\" allowfullscreen></iframe>";
            html += "</body></html>";

            webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadData(html, "text/html", "utf-8");

        }
    }
}
