package org.dhbw.se.movietunes.player;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import org.dhbw.se.movietunes.SimilarSongsActivity;
import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.http.SpotifyCommunication;

import static android.support.v4.content.ContextCompat.startActivity;

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
