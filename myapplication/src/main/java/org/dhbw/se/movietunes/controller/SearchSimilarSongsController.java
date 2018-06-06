package org.dhbw.se.movietunes.controller;

import java.util.List;
import org.dhbw.se.movietunes.http.SpotifyCommunication;
import org.dhbw.se.movietunes.model.Song;

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

