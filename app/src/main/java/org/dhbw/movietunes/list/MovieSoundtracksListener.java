package org.dhbw.movietunes.list;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.player.SpotifyPlayer;
import org.dhbw.movietunes.player.YoutubePlayer;

public class MovieSoundtracksListener implements View.OnClickListener{

  Activity activity;
  Song song;

  public MovieSoundtracksListener(Activity activity, Song song) {
    this.activity = activity;
    this.song = song;
  }

  @Override
  public void onClick(View v) {
    PopupMenu popupMenu =  new PopupMenu(activity, v);
    MenuInflater inflater = popupMenu.getMenuInflater();
    inflater.inflate(R.menu.popup_menu_movie_soundtracks, popupMenu.getMenu());

    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
      public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.spotify:
            new SpotifyPlayer(activity, song.getSongTitle(), song.getUri()).play();
            break;
          case R.id.youTube:
            new YoutubePlayer(activity, song.getSongTitle()).play();
            break;
          case R.id.similar:
            Intent intent = new Intent(activity.getApplicationContext(), ResultSimilarSongsActivity.class);
            intent.putExtra(ResultSimilarSongsActivity.EXTRA_MESSAGE, song.getTrackId());
            activity.startActivity(intent);
            break;
          case R.id.facebook:
            String ShareBody = "I love Movie Tunes!";
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Song found on Movie tunes");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, ShareBody);
            activity.startActivity(Intent.createChooser(sharingIntent, activity.getResources().getString(R.string.share_using)));
            break;
        }
        return true;
      }
    });

    popupMenu.show();
  }
}
