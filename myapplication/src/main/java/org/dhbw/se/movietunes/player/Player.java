package org.dhbw.se.movietunes.player;

import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.http.HttpCommunication;

public class Player {
    protected String songTitle;
    protected HttpCommunication httpCommunication;
    protected String uri;
    protected Extractor extractor;


    public Player(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
    public void play(){

    }



}
