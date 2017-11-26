package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.controller.SearchByTitleController;

import org.dhbw.se.movietunes.logic.Controller;

public class SearchResultActivity extends AppCompatActivity {
    View intro;
    TextView searchResult;
    Movie movieSoundtrack;
    Intent goToNextActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Controller logic = Controller.getInstance();

       //List<Song> songs = new SearchByTitleController (getApplicationContext()).lookupSoundtrack("atatat");


        setContentView(R.layout.activity_search_result);
        intro = findViewById(R.id.intro_text);
        Intent intent = getIntent();
        //movieSoundtrack=intent.getParcelableExtra(MainActivity.EXTRA_MESSAGE);
        String movieTitle = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView movie = (TextView)findViewById(R.id.movie);
        movie.setText(movieTitle);
        //movie.setText(movieSoundtrack.getMovieTitle());
        searchResult = (TextView) findViewById(R.id.search_result);
        //searchResult.setText(movieSoundtrack.getSoundtracks().toString());


        View showSimilarSongs=findViewById(R.id.show_similar_songs);
        showSimilarSongs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Intent Intent1 = new Intent(getApplicationContext(), SimilarSongsActivity.class);
                //startActivity(Intent1);
                goToNextActivity = new Intent(getApplicationContext(), SimilarSongsActivity.class);
                startActivity(goToNextActivity);

            }
        });
    }
    }


