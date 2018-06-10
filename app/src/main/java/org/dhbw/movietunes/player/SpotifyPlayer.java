package org.dhbw.movietunes.player;

import android.app.Activity;

public class SpotifyPlayer extends Player {
  String preUri;

  public SpotifyPlayer(Activity activity, String songTitle, String uri) {
    super(activity, songTitle);
    this.preUri = uri;

  }

  public SpotifyPlayer(Activity activity, String songTitle) {
    super(activity, songTitle);
  }

  @Override
  public String createUri() {
    if (preUri.isEmpty()) {
      return "";
    }

    return preUri;
  }

}
