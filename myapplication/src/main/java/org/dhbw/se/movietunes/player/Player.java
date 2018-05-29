package org.dhbw.se.movietunes.player;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.http.HttpCommunication;

public abstract class Player extends AppCompatActivity {
    protected String songTitle;
    protected String uri;



    public Player(String songTitle) {
        this.songTitle = songTitle;
    }
public abstract  void play();


    public abstract String createUri();
}
