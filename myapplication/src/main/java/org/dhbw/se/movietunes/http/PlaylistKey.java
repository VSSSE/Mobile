package org.dhbw.se.movietunes.http;

public class PlaylistKey {
    private String userId;
    private String playlistId;
    private String spotifyUrl;

    public String getSpotifyUrl() {
        return spotifyUrl;
    }

    public void setSpotifyUrl(String spotifyUrl) {
        this.spotifyUrl = spotifyUrl;
    }

    public PlaylistKey(String userId, String playlistId, String spotifyUrl) {
        if (userId == null || playlistId == null) {
            throw new IllegalArgumentException("null values not allowed");
        }

        this.userId = userId;
        this.playlistId = playlistId;
        this.spotifyUrl=spotifyUrl;

    }

    public String getUserId() {
        return userId;
    }

    public String getPlaylistId() {
        return playlistId;
    }
}
