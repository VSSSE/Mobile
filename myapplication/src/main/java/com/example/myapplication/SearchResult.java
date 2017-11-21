package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.R.id.message;
import static com.example.myapplication.R.id.intro_text;
import static com.example.myapplication.R.id.search_result;

public class SearchResult extends AppCompatActivity {
    View intro;
    TextView searchResult;
    MovieSoundtrack movieSoundtrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        intro = findViewById(R.id.intro_text);
        Intent intent = getIntent();
        movieSoundtrack=intent.getParcelableExtra(MainActivity.EXTRA_MESSAGE);
        //String movieTitle = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView movie = (TextView)findViewById(R.id.movie);
        movie.setText(movieSoundtrack.getMovieTitle());
        TextView searchResult = (TextView) findViewById(R.id.search_result);
        searchResult.setText(movieSoundtrack.getSoundtracks().toString());

        }
    }

