package com.example.brianbystrom.gamesearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class GameDetails extends AppCompatActivity implements GetGameInfoAsync.IData, SetImageAsync.IData {

    Button finish_btn, trailer_btn, similar_btn;
    ImageView image;
    TextView title, genre, overview_label;
    String created_URL;
    ScrollView overview;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        if(getIntent().getExtras() != null) {
            String id = (String) getIntent().getExtras().getString("GAME_ID");
            Log.d("GAME ID", "ID: " + id);


            created_URL = "http://thegamesdb.net/api/GetGame.php?id=" + id;
            new GetGameInfoAsync(GameDetails.this).execute(created_URL);

        }



    }

    public void setupData(final ArrayList<Data> s) {

        finish_btn = (Button) findViewById(R.id.finish_btn);
        trailer_btn = (Button) findViewById(R.id.trailer_btn);
        similar_btn = (Button) findViewById(R.id.similar_btn);

        title = (TextView) findViewById(R.id.game_title_label);
        genre = (TextView) findViewById(R.id.genre_label);
        overview = (ScrollView) findViewById(R.id.overview_scrollview);
        overview.removeAllViews();

        String overviewText = s.get(0).getOverview().toString();

        TextView tv = new TextView(GameDetails.this);
        tv.setText(overviewText);
        overview.addView(tv);

        title.setText(s.get(0).getTitle().toString());

        trailer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGameTrailer = new Intent(GameDetails.this, GameTrailer.class);
                toGameTrailer.putExtra("TRAILER_URL", s.get(0).getTrailer().toString());
                startActivity(toGameTrailer);
            }
        });

        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        similar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ArrayList<String> genres = s.get(0).getGenres();
        String genreString = "";

        for (int g = 0; g < genres.size(); g++) {
            genreString += genres.get(g).toString() + " ";
        }
        genre.setText("Genre: " + genreString + "\nPublisher: " + s.get(0).getPublisher().toString());
        Log.d("DEMO", s.get(0).toString());

        new SetImageAsync(GameDetails.this).execute(s.get(0).getUrlToImage().toString());

    }

    public void setupImage(Bitmap bitmap) {

        ImageView game_image;
        game_image = (ImageView) this.findViewById(R.id.game_image);
        game_image.setImageBitmap(bitmap);

        finish_btn = (Button) findViewById(R.id.finish_btn);
        trailer_btn = (Button) findViewById(R.id.trailer_btn);
        similar_btn = (Button) findViewById(R.id.similar_btn);

        title = (TextView) findViewById(R.id.game_title_label);
        genre = (TextView) findViewById(R.id.genre_label);
        overview = (ScrollView) findViewById(R.id.overview_scrollview);
        overview_label = (TextView) findViewById(R.id.overview_label);

        pb = (ProgressBar) findViewById(R.id.game_detail_progress_bar);

        pb.setVisibility(GONE);
        title.setVisibility(VISIBLE);
        game_image.setVisibility(VISIBLE);
        overview_label.setVisibility(VISIBLE);
        overview.setVisibility(VISIBLE);
        genre.setVisibility(VISIBLE);
        finish_btn.setVisibility(VISIBLE);
        trailer_btn.setVisibility(VISIBLE);
        similar_btn.setVisibility(VISIBLE);







    }

}

