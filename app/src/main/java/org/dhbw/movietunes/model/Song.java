package org.dhbw.movietunes.model;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Song {
  private String trackId;
  private String songTitle;
  private String artist;
  private String duration;
  private String uri;
  private String imageUri;

  public Song(String trackId, String songTitle, String artist, String duration, String uri, String imageUri) {
    this.trackId = trackId;
    this.songTitle = songTitle;
    this.artist = artist;
    this.duration = duration;
    this.uri = uri;
    this.imageUri = imageUri;
  }

  public String getDuration() {
    return duration;
  }

  public String getTrackId() {
    return trackId;
  }

  public String getSongTitle() {
    return songTitle;
  }

  public String getArtist() {
    return artist;
  }

  public String getUri() {
    return this.uri;
  }

  public String getImageUri() {
    return imageUri;
  }

}
