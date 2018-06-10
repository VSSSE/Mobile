package org.dhbw.movietunes.player;

import android.app.Activity;

public class YoutubePlayer extends Player {
  public YoutubePlayer(Activity activity, String songTitle) {
    super(activity, songTitle);
  }

  @Override
  public String createUri() {
    return "";
  }
}
