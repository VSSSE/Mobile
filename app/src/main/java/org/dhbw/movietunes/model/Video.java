package org.dhbw.movietunes.model;

public class Video {

String videoID;
String title;

  public Video(String videoID, String title) {
    this.videoID = videoID;
    this.title = title;
  }

  public String getVideoID() {
    return videoID;
  }

  public String getTitle() {
    return title;
  }

  public boolean equalsTo(Video object2) {

    return object2 != null &&
            (videoID.equals(object2.videoID)) &&
            (title.equals(object2.title));
  }

  //https://www.youtube.com/watch?v=
}
