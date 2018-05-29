package org.dhbw.se.movietunes.player;

public class YoutubePlayer extends Player {
    public YoutubePlayer(String songTitle) {
        super(songTitle);
    }


    @Override
    protected String createUri() {
        return "";
    }
}
