package org.dhbw.movietunes.player;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public abstract class Player {
  protected String songTitle;
  protected String uri;
  protected Activity activity;

  protected Player(Activity activity, String songTitle) {
    this.songTitle = songTitle;
    this.activity = activity;
    uri = createUri();
    play();
  }

  public void play() {
    this.uri = createUri();
    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
  }

  public abstract String createUri();
}
