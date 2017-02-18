package com.example.brianbystrom.gamesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class SimilarGames extends AppCompatActivity implements GetGameInfoAsync.IData {

    int sGamesCounter = 0;
    int similarGames = 0;
    LinearLayout ll;
    ScrollView sv;
    Button finish_btn;

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

        sv = (ScrollView) findViewById(R.id.similar_games_scrollview);
        sv.removeAllViews();
        ll = new LinearLayout(SimilarGames.this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.removeAllViews();

        if(getIntent().getExtras() != null) {
            ArrayList<String> games = (ArrayList<String>) getIntent().getExtras().getStringArrayList("SIMILAR_GAMES");
            similarGames = games.size();

            Log.d("SIZE", games.size() + "///");
            for (int i = 0; i < games.size(); i++) {
                String url = "http://thegamesdb.net/api/GetGame.php?id=" + games.get(i).toString();
                Log.d("URL", url);
                new GetGameInfoAsync(SimilarGames.this).execute(url);
            }

        }
    }

    public void setupData(final ArrayList<Data> s) {

        sGamesCounter++;
        Log.d("LIST", s.get(0).getTitle());
        TextView tv = new TextView(SimilarGames.this);
        tv.setText(s.get(0).getTitle() + ", Released On: " + s.get(0).getReleaseDate() + ", Platform: " + s.get(0).getPlatform());
        ll.addView(tv);

        if (sGamesCounter == similarGames) {
            sv.addView(ll);
        }

    }
}
