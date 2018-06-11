package org.dhbw.movietunes.player;

import android.app.Activity;
import org.dhbw.movietunes.http.YoutubeCommunication;
import org.dhbw.movietunes.model.Video;

public class YoutubePlayer extends Player {
  YoutubeCommunication ytCom;
  public YoutubePlayer(Activity activity) {
    super(activity);
    ytCom = new YoutubeCommunication();
  }


  @Override
  public String createUri(String searchString) {
    Video thatVideo = ytCom.findFirstVideo(searchString);

    if (thatVideo != null) {
      return "https://www.youtube.com/watch?v=" + thatVideo.getVideoID();
    } else {
      return "https://www.youtube.com/watch?v=VideoNotFound";
    }
  }
}
