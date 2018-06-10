package org.dhbw.movietunes.model;

public class PlaylistKey {
  private String userId;
  private String playlistId;
  private String spotifyUrl;
  private String playlistName;

  public PlaylistKey(String userId, String playlistId, String playlistName, String spotifyUrl) {
    this.userId = userId;
    this.playlistId = playlistId;
    this.spotifyUrl = spotifyUrl;
    this.playlistName = playlistName;
  }

  public String getSpotifyUrl() {
    return spotifyUrl;
  }

  public String getUserId() {
    return userId;
  }

  public String getPlaylistId() {
    return playlistId;
  }

  public String getPlaylistName() {
    return playlistName;
  }
}
