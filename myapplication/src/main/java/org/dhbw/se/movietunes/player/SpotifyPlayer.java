package org.dhbw.se.movietunes.player;

import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.http.SpotifyCommunication;

public class SpotifyPlayer extends Player {
   


    public SpotifyPlayer(String songTitle) {
        super(songTitle);
        this.uri = createUri();
    }

   public SpotifyPlayer(String songTitle, String uri){
        super(songTitle);
        this.uri = uri;

   }

    @Override
    public String getSongTitle() {
        return super.getSongTitle();
    }

    @Override
    public void setSongTitle(String songTitle) {
        super.setSongTitle(songTitle);
    }
    @Override
    public void play(){

    }


    public String createUri() {

        return "";
    }
}
