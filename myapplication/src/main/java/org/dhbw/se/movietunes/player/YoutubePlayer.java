package org.dhbw.se.movietunes.player;

public class YoutubePlayer extends Player {
    public YoutubePlayer(String songTitle) {
        super(songTitle);
        this.uri = createUri();
    }


    @Override
    protected String createUri() {
        return "";
    }
}
