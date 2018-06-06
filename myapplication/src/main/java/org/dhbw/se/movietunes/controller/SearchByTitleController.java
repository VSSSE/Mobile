package org.dhbw.se.movietunes.controller;

import android.content.Context;
import android.os.AsyncTask;

import org.dhbw.se.movietunes.SoundtrackSearchResult;
import org.dhbw.se.movietunes.http.PlaylistKey;
import org.dhbw.se.movietunes.http.SpotifyCommunication;
import org.dhbw.se.movietunes.model.Song;
import org.dhbw.se.movietunes.model.Soundtrack;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//import com.example.movietunes.R;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

class AsyncHttp extends AsyncTask<String, Integer, String> {
    private final static Logger LOGGER = Logger.getLogger(AsyncHttp.class.getName());
    protected String doInBackground(String... params) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://www.google.de")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,"SearchByTitle", e);
            return "ERROR";
        }
    }
}


public class SearchByTitleController {

    private final static Logger LOGGER = Logger.getLogger(SearchByTitleController.class.getName());
    Context appContext;

    SpotifyCommunication spotifyCommunication;


    public SearchByTitleController(Context appContext) {
        this.appContext = appContext;
        spotifyCommunication = new SpotifyCommunication();

    }

    private Soundtrack soundtrack;
    private Song song;

    public SearchByTitleController() {
        spotifyCommunication=new SpotifyCommunication();
    }


    public Soundtrack getSoundtrack() {
        return soundtrack;
    }

    public void setSoundtrack(Soundtrack soundtrack) {
        this.soundtrack = soundtrack;
    }

    public  SoundtrackSearchResult searchTracklist (String input) {

        PlaylistKey playlistKey = spotifyCommunication.findPlaylist(input);
        String url=playlistKey.getSpotifyUrl();
        List<Song> songs=spotifyCommunication.getSongsFromPlaylist(playlistKey);
        SoundtrackSearchResult trackResult=new SoundtrackSearchResult(url, songs);
        return trackResult;

    }




    public Song getSoundtrackAsText() {
        return song;
    }

    public void setSoundtrackAsText(Song song) {
        this.song = song;
    }


}
