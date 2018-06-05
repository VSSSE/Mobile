package org.dhbw.se.movietunes.player;

public class SpotifyPlayer extends Player{

protected String songTitle;
protected String uri;

    public SpotifyPlayer(String songTitle, String uri){
        super(songTitle);
        this.uri = uri;

    }

    public String getUri() {
        return uri;
    }

    @Override
    public void play() {
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(this.uri)));

    }





    @Override
    public String createUri() {

        return this.uri;
    }

}
