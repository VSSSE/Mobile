package org.dhbw.movietunes.controller;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.SoundtrackSearchResult;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieSoundtracksController extends AsyncTask<String, Integer, SoundtrackSearchResult> {

  private ResultMovieSoundtracksActivity activity;

  public SearchMovieSoundtracksController(ResultMovieSoundtracksActivity activity) {
    this.activity = activity;
  }

  @Override
  protected SoundtrackSearchResult doInBackground(String... params) {
    if (params.length != 1) {
      throw new HttpException("Expected 1 prameter, got " + params.length);
    }
    SpotifyCommunication spotifyCommunication = new SpotifyCommunication();

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
    List<Song> currentSongList = new ArrayList<>(result.getSongs());
    String[] strings = new String[currentSongList.size()];

    for (int i = 0; i < currentSongList.size(); i++) {
      Song song = currentSongList.get(i);
      strings[i] = song.getSongTitle() + " (Duration:" + song.getDuration() + ")"
              + song.getSinger();
    }

    activity.setCurrentSongList(currentSongList);
    activity.setStrackSearchResult(result);
    activity.setStrings(strings);

    ArrayAdapter adapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, strings);
    ListView list = activity.findViewById(R.id.soundtrack_list_view);
    list.setAdapter(adapter);
  }

}
