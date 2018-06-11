package org.dhbw.movietunes.controller;

import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieTitleActivity;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.SongAdapter;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchSimilarSongsController extends AsyncSearchController {

  public SearchSimilarSongsController(ResultSimilarSongsActivity activity) {
    super(activity);
  }

  @Override
  protected List<Object> search(String searchString) {
    SpotifyCommunication spotifyCommunication = new SpotifyCommunication();

    return new ArrayList<Object>(spotifyCommunication.getRecommendations(searchString));
  }

  @Override
  protected void postResult(List<Object> result) {
    SongAdapter adapter = new SongAdapter(activity, (ArrayList<Song>)(List) result);
    ListView list = activity.findViewById(R.id.similarSongs_list_view);
    list.setAdapter(adapter);
  }

}

