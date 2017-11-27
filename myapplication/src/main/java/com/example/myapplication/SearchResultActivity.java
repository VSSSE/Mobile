package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        controller=new SearchByTitleController(getApplicationContext());

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);

        mListView = (ListView) findViewById(R.id.soundtrack_list_view);
// 1
        List<Song> songArrayList = controller.lookupSoundtrack("");
// 2
        String[] strings = new String[songArrayList.size()];
// 3
        for(int i = 0; i < songArrayList.size(); i++){
            Song song = songArrayList.get(i);
            strings[i] = song.getSongTitle() + "("+ song.getComposer() + ")";
        }
// 4
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        mListView.setAdapter(adapter);

    }
    }


