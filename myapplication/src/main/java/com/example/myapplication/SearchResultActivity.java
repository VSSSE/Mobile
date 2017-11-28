package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.dhbw.se.movietunes.controller.SearchByTitleController;
import org.dhbw.se.movietunes.model.Song;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    View intro;
    TextView searchResult;
    Intent goToNextActivity;
    private ListView mListView;
    private SearchByTitleController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        controller = new SearchByTitleController(getApplicationContext());

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);
        String movieTitle = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView movie = (TextView) findViewById(R.id.movie);
        movie.setText(movieTitle);

        mListView = (ListView) findViewById(R.id.soundtrack_list_view);
// 1
        List<Song> songArrayList = controller.lookupSoundtrack("");
// 2
        String[] strings = new String[songArrayList.size()];
// 3
        for (int i = 0; i < songArrayList.size(); i++) {
            Song song = songArrayList.get(i);
            strings[i] = song.getSongTitle() + " (Duration:" + song.getDuration() + ")";
        }
// 4
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String songString = (String) mListView.getItemAtPosition(position);

                // Show Alert
                // Toast.makeText(getApplicationContext(),
                //         "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                //         .show();

                Intent intent = new Intent(getApplicationContext(), SimilarSongsActivity.class);
                intent.putExtra("SONG_STRING", songString);
                startActivity(intent);
            }
        });


        /*
        View showSimilarSongs = findViewById(R.id.show_similar_songs);
        showSimilarSongs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intent Intent1 = new Intent(getApplicationContext(), SimilarSongsActivity.class);
                //startActivity(Intent1);
                goToNextActivity = new Intent(getApplicationContext(), SimilarSongsActivity.class);
                startActivity(goToNextActivity);

            }
        });

        */


    }

}


