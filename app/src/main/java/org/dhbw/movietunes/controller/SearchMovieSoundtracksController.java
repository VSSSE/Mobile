package org.dhbw.movietunes.controller;

import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.ResultMovieTitleActivity;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.SongAdapter;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieSoundtracksController extends AsyncSearchController {

  public SearchMovieSoundtracksController(ResultMovieSoundtracksActivity activity) {
    super(activity);
  }

  @Override
  protected Boolean search(String searchString) {
    SpotifyCommunication spotifyCommunication = new SpotifyCommunication();

    return new ArrayList<Object>(spotifyCommunication.findSoundtracks(searchString));
  }

  @Override
  protected void postResult(Boolean result) {
    if(result) {
      ((ResultMovieSoundtracksActivity)activity).updateList();
    }
  }

}

