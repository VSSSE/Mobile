package org.dhbw.se.movietunes.player;

import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.http.HttpCommunication;

public abstract class Player {
    protected String songTitle;
    protected HttpCommunication httpCommunication;
    protected String uri;



    public Player(String songTitle) {
        this.songTitle = songTitle;
    }


    public void play()
    {

        //TODO open in browser ...
        if(uri == "")
            uri = createUri();
    }

    protected abstract String createUri();



}
