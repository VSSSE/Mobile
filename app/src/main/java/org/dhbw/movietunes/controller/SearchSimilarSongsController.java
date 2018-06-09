package org.dhbw.movietunes.controller;

import java.util.List;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchSimilarSongsController {
  private SpotifyCommunication spotifyCommunication;

  public SearchSimilarSongsController() {
    spotifyCommunication = new SpotifyCommunication();
  }

  public List<Song> findSimilarSongs(String trackId) {
    return spotifyCommunication.getRecommendations(trackId);
  }
}

