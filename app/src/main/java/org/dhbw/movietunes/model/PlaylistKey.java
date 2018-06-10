package org.dhbw.movietunes.model;

public class PlaylistKey {
  private String userId;
  private String playlistId;
  private String spotifyUrl;

  public PlaylistKey(String userId, String playlistId, String spotifyUrl) {
    if (userId == null || playlistId == null) {
      throw new IllegalArgumentException("Null values are not allowed!");
    }

    this.userId = userId;
    this.playlistId = playlistId;
    this.spotifyUrl = spotifyUrl;
  }

  public String getSpotifyUrl() {
    return spotifyUrl;
  }

  public void setSpotifyUrl(String spotifyUrl) {
    this.spotifyUrl = spotifyUrl;
  }

  public String getUserId() {
    return userId;
  }

  public String getPlaylistId() {
    return playlistId;
  }
}