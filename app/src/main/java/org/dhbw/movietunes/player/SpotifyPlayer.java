package org.dhbw.movietunes.player;

import android.app.Activity;

public class SpotifyPlayer extends Player {
  String preUri = "";

  public SpotifyPlayer(Activity activity, String uri) {
    super(activity);
    this.preUri = uri;

  }

  @Override
  public String createUri(String searchString) {
    if (preUri.isEmpty()) {
      //TODO get URL
      return "";
    }

    return preUri;
  }

}
