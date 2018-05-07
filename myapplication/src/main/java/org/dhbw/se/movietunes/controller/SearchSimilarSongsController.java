package org.dhbw.se.movietunes.controller;

import android.content.Context;

import org.dhbw.se.movietunes.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchSimilarSongsController {
    Context appContext;

    public SearchSimilarSongsController(Context appContext) {
        this.appContext = appContext;
    }


    public List<Song> findSimilarSongs(String input) {

        List<Song> result = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Song song = new Song();
            song.setSongTitle("similar to " + input + " " + i);
            result.add(song);
        }


        return result;
    }

}

