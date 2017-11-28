package com.example.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.dhbw.se.movietunes.controller.SearchSimilarSongsController;
import org.dhbw.se.movietunes.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class SimilarSongsActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String songString = intent.getStringExtra("SONG_STRING");


        setContentView(R.layout.similar_songs);
        View intro = findViewById(R.id.intro);
        ListView similarSongsView = (ListView) findViewById(R.id.similarSongs_list_view);

        SearchSimilarSongsController controller = new SearchSimilarSongsController(getApplicationContext());

        List<String> similars = new ArrayList<>();
        for (Song song : controller.findSimilarSongs(songString)) {
            similars.add(song.getSongTitle());
        }


        //String[] similars = {"Similar to " + songString + " 1", "Similar to " + songString + " 2", "Similar to " + songString + " 3"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, similars);
        similarSongsView.setAdapter(adapter);

    }
}
