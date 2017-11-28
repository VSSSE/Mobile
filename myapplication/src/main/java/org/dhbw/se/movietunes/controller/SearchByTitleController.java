package org.dhbw.se.movietunes.controller;

import android.content.Context;

//import com.example.movietunes.R;
import org.dhbw.se.movietunes.model.Song;
import org.dhbw.se.movietunes.model.Soundtrack;

import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchByTitleController {

    Context appContext;

    public SearchByTitleController(Context appContext) {
        this.appContext = appContext;
    }

    private Soundtrack soundtrack;
    private Song song;


    public Soundtrack getSoundtrack() {
        return soundtrack;
    }

    public void setSoundtrack(Soundtrack soundtrack) {
        this.soundtrack = soundtrack;
    }

    public List<Song> lookupSoundtrack(String input) {

        //InputStream inputStream = getResources().openRawResource();

        InputStream in = appContext.getResources().openRawResource(R.raw.songs);
        InputStreamReader reader = new InputStreamReader(in);

        Gson gson = new GsonBuilder().create();
        List<Song> songs = Arrays.asList(gson.fromJson(reader, Song[].class));

        System.out.println("AAAAAAAAAAAAAAAA song read successfully");



           return songs;


    }

    public Song getSoundtrackAsText() {
        return song;
    }

    public void setSoundtrackAsText(Song song) {
        this.song = song;
    }


}
