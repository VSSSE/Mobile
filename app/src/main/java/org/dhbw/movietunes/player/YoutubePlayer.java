package org.dhbw.movietunes.player;

public class YoutubePlayer extends Player {
  public YoutubePlayer(String songTitle) {
    super(songTitle);
  }

  @Override
  public void play() {

  }

  @Override
  public String createUri() {
    return "";
  }
}
