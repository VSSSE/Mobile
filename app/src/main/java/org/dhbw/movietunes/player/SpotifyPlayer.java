package org.dhbw.movietunes.player;

public class SpotifyPlayer extends Player {

  public SpotifyPlayer(String songTitle, String uri) {
    super(songTitle);
    this.uri = uri;

  }

  public String getUri() {
    return uri;
  }

  @Override
  public void play() {

  }

  @Override
  public String createUri() {

    return this.uri;
  }

}
