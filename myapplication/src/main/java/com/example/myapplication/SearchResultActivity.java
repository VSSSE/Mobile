package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.dhbw.se.movietunes.logic.Logic;

import static android.R.id.message;
import static com.example.myapplication.R.id.intro_text;
import static com.example.myapplication.R.id.search_result;

public class SearchResultActivity extends AppCompatActivity {
    View intro;
    TextView searchResult;
    MovieSoundtrack movieSoundtrack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logic logic = Logic.getInstance();

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
                Intent Intent = new Intent(getApplicationContext(), SimilarSongsActivity.class);
                startActivity(Intent);

            }
        });
    }
    }


