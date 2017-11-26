package com.example.myapplication;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class Songcontroller {
    private List<Song> allSongs;
    private Song song;
    private Activity activity;
    private static Songcontroller instance = null;
    public Songcontroller(){
        this.song=song;



    }
    public List<Song> lookupSoundtrack(String input){
        List<Song> soundtrack=new ArrayList<Song>();
        soundtrack.add(new Song("Song11"));
        soundtrack.add(new Song("Song12fff"));
        soundtrack.add(new Song("Song13sss"));
        soundtrack.add(new Song("Song14ggg"));
        return soundtrack;

    }

    public List<Song> findSimilarSongs(String songId){
        List<Song> similarSongs=new ArrayList<Song>();
        similarSongs.add(new Song("Song11"));
        similarSongs.add(new Song("Song12fff"));
        similarSongs.add(new Song("Song13sss"));
        similarSongs.add(new Song("Song14ggg"));
        return similarSongs;

    }
    public static Songcontroller getInstance() {
        if (instance==null){
            instance = new Songcontroller();
        }
        return instance;
    }



    //public double getTotalTime(){

    }



