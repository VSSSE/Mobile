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
    Charset UTF8_CHARSET = Charset.forName("UTF-8");
    this.trackId = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_TrackId)), UTF8_CHARSET);
    this.songTitle = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_SongTitle)), UTF8_CHARSET);
    this.artist = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_Artist)), UTF8_CHARSET);
    this.duration = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_Duration)), UTF8_CHARSET);
    this.uri = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_Uri)), UTF8_CHARSET);
    this.imageUri = new String(cursor.getBlob(cursor.getColumnIndexOrThrow(_ImageUri)), UTF8_CHARSET);
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