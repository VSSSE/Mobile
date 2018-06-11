package org.dhbw.movietunes.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.util.ArrayList;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.http.ImageLoader;
import org.dhbw.movietunes.model.Movie;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.player.SpotifyPlayer;
import org.dhbw.movietunes.player.YoutubePlayer;
import org.dhbw.movietunes.utils.Utils;

public class MovieAdapter extends BaseAdapter {

  private static LayoutInflater inflater = null;
  private Activity activity;
  private ArrayList<Movie> data;

  public MovieAdapter(Activity a, ArrayList<Movie> d) {
    activity = a;
    data = d;
    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public int getCount() {
    return data.size();
  }

  public Object getItem(int position) {
    return position;
  }

  public long getItemId(int position) {
    return position;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;
    if (convertView == null)
      vi = inflater.inflate(R.layout.list_row_movie, null);

    TextView title = vi.findViewById(R.id.title); // title

    final Movie movie = data.get(position);

    // Setting all values in listview
    title.setText(movie.getMovieTitle());

    vi.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        PopupMenu popupMenu = new PopupMenu(activity, v);
        MenuInflater inflater = popupMenu.getMenuInflater();

        inflater.inflate(R.menu.popup_menu_movie_title, popupMenu.getMenu());


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
          public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
              case R.id.searchSoundtracks:
                Intent intent = new Intent(activity.getApplicationContext(), ResultMovieSoundtracksActivity.class);
                intent.putExtra(ResultMovieSoundtracksActivity.EXTRA_MESSAGE, movie.getMovieTitle());
                activity.startActivity(intent);
                break;
              case R.id.facebook:
                Utils.ShareText(activity,"I found " + movie.getMovieTitle()
                        + " with Movie Tunes!");
                break;
                default:
                  return false;
            }
            return true;
          }
        });

        popupMenu.show();
      }
    });
    return vi;
  }

}