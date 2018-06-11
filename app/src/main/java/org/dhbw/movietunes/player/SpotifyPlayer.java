package org.dhbw.movietunes.player;

import android.app.Activity;

public class SpotifyPlayer extends Player {
  String preUri = "";

  public SpotifyPlayer(Activity activity, String searchString, String uri) {
    super(activity, searchString);
    this.preUri = uri;

  }

  public SpotifyPlayer(Activity activity, String searchString) {
    super(activity, searchString);
  }

  @Override
  public String createUri() {
    if (preUri.isEmpty()) {
      //TODO get URL
      return "";
    }

    return preUri;
  }

}
