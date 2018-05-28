package org.dhbw.se.movietunes.player;

public class YoutubePlayer extends Player {
    public YoutubePlayer(String songTitle) {
        super(songTitle);
        this.uri = createUri();
    }

    @Override
    public String getSongTitle() {
        return super.getSongTitle();
    }

    @Override
    public void setSongTitle(String songTitle) {
        super.setSongTitle(songTitle);
    }

    private String createUri() {
        return "";
    }
}
