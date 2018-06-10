package org.dhbw.movietunes.controller;

import android.os.AsyncTask;
import java.util.List;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.SoundtrackSearchResult;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieSoundtracksController extends AsyncTask<String, Integer, SoundtrackSearchResult> {

  private SpotifyCommunication spotifyCommunication;

  public SearchMovieSoundtracksController() {
    spotifyCommunication = new SpotifyCommunication();
  }

  @Override
  protected SoundtrackSearchResult doInBackground(String... params) {
    if (params.length != 1) {
      throw new HttpException("Only 1 prameter expected");
    }

    PlaylistKey playlistKey = spotifyCommunication.findPlaylist(params[0]);

    String url = playlistKey.getSpotifyUrl();
    List<Song> songs = spotifyCommunication.getSongsFromPlaylist(playlistKey);
    return new SoundtrackSearchResult(url, songs);
  }

  @Override
  protected void onPreExecute() {

  }

  @Override
  protected void onProgressUpdate(Integer... values) {

  }

  @Override
  protected void onPostExecute(SoundtrackSearchResult result) {

  }

}
