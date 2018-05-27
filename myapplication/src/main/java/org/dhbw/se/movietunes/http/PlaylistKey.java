package org.dhbw.se.movietunes.http;

public class PlaylistKey {
    private String userId;
    private String playlistId;


    public PlaylistKey(String userId, String playlistId) {
        if (userId == null || playlistId == null) {
            throw new IllegalArgumentException("null values not allowed");
        }

        this.userId = userId;
        this.playlistId = playlistId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlaylistId() {
        return playlistId;
    }
}
