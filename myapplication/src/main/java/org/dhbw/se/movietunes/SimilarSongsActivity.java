package org.dhbw.se.movietunes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

import org.dhbw.se.movietunes.controller.SearchSimilarSongsController;
import org.dhbw.se.movietunes.model.Song;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class SimilarSongsActivity extends MainActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String trackId = intent.getStringExtra("TRACK_ID");


        setContentView(R.layout.similar_songs);
        //View intro = findViewById(R.id.intro);
        ListView similarSongsView = (ListView) findViewById(R.id.similarSongs_list_view);

        SearchSimilarSongsController controller = new SearchSimilarSongsController(getApplicationContext());

        List<String> similars = new ArrayList<>();
        for (Song song : controller.findSimilarSongs(trackId)) {
            similars.add(song.getSongTitle()+"Duration: "+song.getDuration()+", "+song.getSinger());
        }


        //String[] similars = {"Similar to " + songString + " 1", "Similar to " + songString + " 2", "Similar to " + songString + " 3"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, similars);
        similarSongsView.setAdapter(adapter);

    }

}
