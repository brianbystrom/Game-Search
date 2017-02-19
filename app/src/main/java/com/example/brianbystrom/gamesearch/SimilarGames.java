/*
Assignment #: Homework 05
File Name: SimilarGames.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.gamesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SimilarGames extends AppCompatActivity implements GetGameInfoAsync.IData {

    int sGamesCounter = 0;
    int similarGames = 0;
    LinearLayout ll;
    ScrollView sv;
    Button finish_btn;
    TextView title_tv;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_games);

        finish_btn = (Button) findViewById(R.id.finish_btn);

        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title_tv = (TextView) findViewById(R.id.title_label);


        sv = (ScrollView) findViewById(R.id.similar_games_scrollview);
        sv.removeAllViews();
        ll = new LinearLayout(SimilarGames.this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.removeAllViews();

        if(getIntent().getExtras() != null) {
            ArrayList<String> games = (ArrayList<String>) getIntent().getExtras().getStringArrayList("SIMILAR_GAMES");
            similarGames = games.size();

            String title = (String) getIntent().getExtras().getString("GAME_TITLE");
            title_tv.setText("Games similar to " + title);

            Log.d("SIZE", games.size() + "///");
            for (int i = 0; i < games.size(); i++) {
                String url = "http://thegamesdb.net/api/GetGame.php?id=" + games.get(i).toString();
                Log.d("URL", url);
                new GetGameInfoAsync(SimilarGames.this).execute(url);
            }

        }
    }

    public void setupData(final ArrayList<Data> s) {

        String release_date, titleString, platform;

        try {
            if (s.get(0).getTitle() != null) {
                titleString = s.get(0).getTitle();
            } else {
                titleString = "None";
            }

            if (s.get(0).getReleaseDate() != null) {
                release_date = s.get(0).getReleaseDate();
            } else {
                release_date = "None";
            }

            if (s.get(0).getPlatform() != null) {
                platform = s.get(0).getPlatform();
            } else {
                platform = "None";
            }


            sGamesCounter++;
            Log.d("LIST", s.get(0).getTitle());
            TextView tv = new TextView(SimilarGames.this);
            tv.setText(s.get(0).getTitle() + ", Released On: " + s.get(0).getReleaseDate() + ", Platform: " + s.get(0).getPlatform());
            ll.addView(tv);
        } catch (Exception e) {
            sGamesCounter++;
        }

        if (sGamesCounter == similarGames) {
            sv.addView(ll);

            pb = (ProgressBar) findViewById(R.id.similar_pb);
            title_tv = (TextView) findViewById(R.id.title_label);
            pb.setVisibility(GONE);
            title_tv.setVisibility(VISIBLE);
            sv.setVisibility(VISIBLE);


        }

    }
}
