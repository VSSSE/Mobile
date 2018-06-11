package org.dhbw.movietunes.player;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.SongAdapter;
import org.dhbw.movietunes.model.Song;

public abstract class Player extends AsyncTask<String, Integer, String> {
  protected Activity activity;


  public Player(Activity activity) {
    this.activity = activity;
  }

  protected abstract String createUri(String searchString);


  @Override
  protected String doInBackground(String... params) {
    if (params.length != 1) {
      throw new HttpException("Expected 1 prameter, got " + params.length);
    }
    return createUri(params[0]);
  }

  @Override
  protected void onPostExecute(String result) {
    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(result)));
  }

}
