package org.dhbw.movietunes.player;

import android.app.Activity;
import android.widget.Toast;

import org.dhbw.movietunes.R;
import org.dhbw.movietunes.http.YoutubeCommunication;
import org.dhbw.movietunes.model.Video;

public class YoutubePlayer extends Player {
  YoutubeCommunication ytCom;

  public YoutubePlayer(Activity activity) {
    super(activity);
  }

  @Override
  public String createUri(String searchString, String searchString2) {
    ytCom = new YoutubeCommunication();
    Video thatVideo = ytCom.findFirstVideo(searchString + " " + searchString2);

    if (thatVideo != null) {
      return "https://www.youtube.com/watch?v=" + thatVideo.getVideoID();
    } else {
      Toast.makeText(activity, R.string.noYoutubeVideo,
              Toast.LENGTH_LONG).show();
      //return "https://www.youtube.com/watch?v=VideoNotFound";
      return null;
    }
  }
}
