/*
Assignment #: Homework 05
File Name: MainActivity.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.gamesearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements GetGamesAsync.IData {

    Button go_btn, search_btn;
    EditText search;
    ProgressBar pb;
    int selected = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_btn = (Button) findViewById(R.id.search_btn);
        go_btn = (Button) findViewById(R.id.go_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String created_URL, search_term;

                search = (EditText) findViewById(R.id.search_term);
                search_term = search.getText().toString();

                pb = (ProgressBar) findViewById(R.id.loading_progress_bar);

                if (!isEmpty(search_term)) {
                    created_URL = "http://thegamesdb.net/api/GetGamesList.php?name=" + search_term;
                    new GetGamesAsync(MainActivity.this).execute(created_URL);
                    pb.setVisibility(VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a game.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void setupData(final ArrayList<Data> s) {

        pb = (ProgressBar) findViewById(R.id.loading_progress_bar);

        try {
            if (s.size() > 0) {


                ListView lv = new ListView(MainActivity.this);
                //ArrayAdapter<Data> adapter = new ArrayAdapter<Data>(this, android.R.layout.simple_list_item_1, s);
                GameAdapter adapter = new GameAdapter(this, R.layout.game_list_layout, s);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });

                LinearLayout ll = new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.removeAllViews();

                RadioGroup rg = new RadioGroup(MainActivity.this);

                for (int i = 0; i < s.size(); i++) {
                    RadioButton rb = new RadioButton(MainActivity.this);
                    rb.setMinimumWidth(300);
                    rb.setText(s.get(i).getTitle().toString() + ", Released in: " + s.get(i).getReleaseDate().toString() + ", Platform: " + s.get(i).getPlatform().toString());
                    rb.setTextSize(10);
                    rb.setId(Integer.parseInt(s.get(i).getId().toString().trim()));
                    Log.d("SET", "ID: " + s.get(i).getId());
                    rg.addView(rb);
                }

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        selected = checkedId;
                        Log.d("ID", "ID: " + checkedId);
                    }
                });

                ll.addView(rg);
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


                ScrollView sv = (ScrollView) findViewById(R.id.game_list_scrollview);
                sv.removeAllViews();
                sv.addView(ll);
                sv.setFillViewport(true);

                pb.setVisibility(GONE);
                sv.setVisibility(VISIBLE);

                go_btn = (Button) findViewById(R.id.go_btn);
                go_btn.setEnabled(true);

                go_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selected > -1) {
                            Intent toGameDetails = new Intent(MainActivity.this, GameDetails.class);
                            Log.d("SELECTED", "SELECTED: " + selected);
                            toGameDetails.putExtra("GAME_ID", String.valueOf(selected));
                            startActivity(toGameDetails);
                        }
                    }
                });

            }
        } catch (Exception e) {
            Log.d("Exception", e.toString());
            Toast.makeText(MainActivity.this, "API timed out or results returned no games, please try again.", Toast.LENGTH_SHORT).show();
            pb.setVisibility(GONE);
        }
    }


}
