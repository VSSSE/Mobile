package org.dhbw.movietunes.model;

import android.database.Cursor;
import android.provider.BaseColumns;
import java.nio.charset.Charset;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Song implements BaseColumns {
  public static final String _TabellenName = "Song";
  public static final String _TrackId = "trackId";
  public static final String _SongTitle = "songTitle";
  public static final String _Artist = "artist";
  public static final String _Duration = "duration";
  public static final String _Uri = "uri";
  public static final String _ImageUri = "imageUri";
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
  public Song(Cursor cursor) {
    this.trackId = cursor.getString(cursor.getColumnIndexOrThrow(_TrackId));
    this.songTitle = cursor.getString(cursor.getColumnIndexOrThrow(_SongTitle));
    this.artist = cursor.getString(cursor.getColumnIndexOrThrow(_Artist));
    this.duration = cursor.getString(cursor.getColumnIndexOrThrow(_Duration));
    this.uri = cursor.getString(cursor.getColumnIndexOrThrow(_Uri));
    this.imageUri = cursor.getString(cursor.getColumnIndexOrThrow(_ImageUri));
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

  public boolean equalsTo(Song object2) {

    return object2 != null &&
            (trackId.equals(object2.trackId)) &&
            (songTitle.equals(object2.songTitle)) &&
            (artist.equals(object2.artist)) &&
            (duration.equals(object2.duration)) &&
            (uri.equals(object2.uri)) &&
            (imageUri.equals(object2.imageUri));
  }

}