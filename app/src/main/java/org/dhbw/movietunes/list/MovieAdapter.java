package org.dhbw.movietunes.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.ResultMovieTitleActivity;
import org.dhbw.movietunes.database.Database;
import org.dhbw.movietunes.model.IsPlayedIn;
import org.dhbw.movietunes.model.Movie;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.utils.Utils;

public class MovieAdapter extends BaseAdapter {

  private static LayoutInflater inflater = null;
  private Activity activity;

  public MovieAdapter(Activity a) {
    activity = a;
    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public int getCount() {
    SQLiteDatabase db = Database.getDB(activity);

    String queryString = "Select count(M." + Movie._ID + ") as anzahl"
            + " FROM " + Movie._TabellenName + " as M,"
            + IsPlayedIn._TabellenName + " as I"
            + " WHERE M." + Movie._MovieTitle + " = I." + IsPlayedIn._MovieName
            + " AND I." + IsPlayedIn._SongName + " = ?";

    String[] args = new String[]{((ResultMovieTitleActivity) activity).getSongTitle()};

    Cursor cursor = db.rawQuery(queryString, args);

    cursor.moveToFirst();

    return cursor.getInt(cursor.getColumnIndexOrThrow("anzahl"));
  }

  public Movie getItem(int position) {
    SQLiteDatabase db = Database.getDB(activity);

    Cursor cursor = db.rawQuery("SELECT " + "*"
            + " FROM " + Movie._TabellenName
            + " WHERE " + Movie._ID + " = " + position
            + ";", null);

    cursor.moveToFirst();

    return new Movie(cursor);
  }

  public long getItemId(int position) {
    SQLiteDatabase db = Database.getDB(activity);

    String queryString = "Select M." + Movie._ID
            + " FROM " + Movie._TabellenName + " as M,"
            + IsPlayedIn._TabellenName + " as I"
            + " WHERE M." + Movie._MovieTitle + " = I." + IsPlayedIn._MovieName
            + " AND I." + IsPlayedIn._SongName + " = ?";

    String[] args = new String[]{((ResultMovieTitleActivity) activity).getSongTitle()};

    Cursor cursor = db.rawQuery(queryString, args);

    cursor.moveToPosition(position);

    return cursor.getLong(cursor.getColumnIndexOrThrow(Song._ID));
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;
    if (convertView == null)
      vi = inflater.inflate(R.layout.list_row_movie, null);

    TextView title = vi.findViewById(R.id.title); // title

    final Movie movie = getItem((int) getItemId(position));

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
                Utils.ShareText(activity, "I found " + movie.getMovieTitle()
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