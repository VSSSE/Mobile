package org.dhbw.se.movietunes.controller;

import java.util.List;
import org.dhbw.se.movietunes.model.SoundtrackSearchResult;
import org.dhbw.se.movietunes.model.PlaylistKey;
import org.dhbw.se.movietunes.http.SpotifyCommunication;
import org.dhbw.se.movietunes.model.Song;
import org.dhbw.se.movietunes.model.Soundtrack;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchByTitleController {

  private SpotifyCommunication spotifyCommunication;
  private Soundtrack soundtrack;
  private Song song;

  public SearchByTitleController() {
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
