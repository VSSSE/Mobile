package org.dhbw.se.movietunes.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;

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

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//import com.example.movietunes.R;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

class AsyncHttp extends AsyncTask<String, Integer, String> {
    protected String doInBackground(String... params) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://www.google.de")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}


public class SearchByTitleController {

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



    public List<Song> lookupSoundtrack_44(String input) {


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try

                {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.google.de")
                            .build();
                    response = client.newCall(request).execute();
                    String s = response.body().string();

                    System.err.println(s);




                    // parse

                    // data

                    // new intent


                } catch (
                        IOException e)

                {
                    e.printStackTrace();
                }
            }
        });
        return new ArrayList<>();

    }



    public List<Song> lookupSoundtrack_OLD(String input) {


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    URL spotifyEndpoint = new URL("https://api.spotify.com/v1/search?q=%22input&type=playlist");

                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) spotifyEndpoint.openConnection();

                    myConnection.getResponseCode();
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);


                        // Success
                        // Further processing here
                    } else {
                        System.out.println("Funktioniert nicht");
                        // Error handling code goes here
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // All your networking logic
                // should be here
            }
        });

        //InputStream inputStream = getResources().openRawResource();

        //InputStream in = appContext.getResources().openRawResource(R.raw.songs);
        //InputStreamReader reader = new InputStreamReader(in);
        //  List<Song> songs = Arrays.asList(gson.fromJson(reader, Song[].class));

        System.out.println("AAAAAAAAAAAAAAAA song read successfully");


        return null;


    }

    public Song getSoundtrackAsText() {
        return song;
    }

    public void setSoundtrackAsText(Song song) {
        this.song = song;
    }


}
