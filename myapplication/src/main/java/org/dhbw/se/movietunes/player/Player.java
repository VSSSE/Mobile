package org.dhbw.se.movietunes.player;

import android.support.v7.app.AppCompatActivity;

public abstract class Player extends AppCompatActivity {
    protected String songTitle;
    protected String uri;



    public Player(String songTitle) {
        this.songTitle = songTitle;
    }
public abstract  void play();


    public abstract String createUri();
}
