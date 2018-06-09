package org.dhbw.movietunes.player;

public abstract class Player {
  protected String songTitle;
  protected String uri;

  public Player(String songTitle) {
    this.songTitle = songTitle;
  }

  public abstract void play();

  public abstract String createUri();
}
