package org.dhbw.movietunes.controller;

import java.util.List;
import org.dhbw.movietunes.list.SoundtrackSearchResult;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.model.Soundtrack;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieSoundtracksController {

  private SpotifyCommunication spotifyCommunication;
  private Soundtrack soundtrack;
  private Song song;

  public SearchMovieSoundtracksController() {
    spotifyCommunication = new SpotifyCommunication();
  }

  public SoundtrackSearchResult searchTracklist(String input) {
    PlaylistKey playlistKey = spotifyCommunication.findPlaylist(input);

    String url = playlistKey.getSpotifyUrl();
    List<Song> songs = spotifyCommunication.getSongsFromPlaylist(playlistKey);
    return new SoundtrackSearchResult(url, songs);
  }

  public Soundtrack getSoundtrack() {
    return soundtrack;
  }

  public void setSoundtrack(Soundtrack soundtrack) {
    this.soundtrack = soundtrack;
  }

  public Song getSong() {
    return song;
  }

  public void setSong(Song song) {
    this.song = song;
  }
}
