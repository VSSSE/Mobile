package org.dhbw.movietunes.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import org.dhbw.movietunes.ResultMovieTitleActivity;
import org.dhbw.movietunes.database.Database;
import org.dhbw.movietunes.http.MovieCommunication;
import org.dhbw.movietunes.model.IsPlayedIn;
import org.dhbw.movietunes.model.IsSimilarTo;
import org.dhbw.movietunes.model.Movie;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieTitleController extends AsyncSearchController {

  public SearchMovieTitleController(ResultMovieTitleActivity activity) {
    super(activity);
  }

  @Override
  protected Boolean search(String searchString) {
    MovieCommunication movieCommunication = new MovieCommunication();

    ArrayList<Movie> result = new ArrayList(movieCommunication.findMovies(searchString));

    SQLiteDatabase db = Database.getDB(activity);

    String[] args = new String[]{searchString};
    Cursor finder = db.rawQuery("Select " + Song._SongTitle
                    + " FROM " + Song._TabellenName + " as S"
                    + " WHERE S." + Song._SongTitle + " = ?"
            , args);

    String songName;
    if (finder.getCount() > 0) {
      finder.moveToFirst();
      songName = finder.getString(finder.getColumnIndexOrThrow(Song._SongTitle));
    } else {
      //TODO search song if not exist

      songName = searchString;

      ContentValues valuesCreate = new ContentValues();

      valuesCreate.put(Song._SongTitle, songName);

      synchronized (db) {
        db.insert(Song._TabellenName, null, valuesCreate);
      }
    }

    for (Movie movie : result) {
      String movieName;

      String[] argsTo = new String[]{movie.getMovieTitle()};
      Cursor finderTo = db.rawQuery("Select " + Movie._MovieTitle
                      + " FROM " + Movie._TabellenName + " as M"
                      + " WHERE M." + Movie._MovieTitle + " = ?"
              , argsTo);

      if (finderTo.getCount() > 0) {
        finderTo.moveToFirst();
        movieName = finderTo.getString(finder.getColumnIndexOrThrow(Movie._MovieTitle));
      } else {

        ContentValues values = new ContentValues();

        values.put(Movie._MovieTitle, movie.getMovieTitle());
        values.put(Movie._MovieUri, movie.getMovieUri());

        synchronized (db) {
          db.insert(Movie._TabellenName, null, values);
        }

        movieName = movie.getMovieTitle();

      }

      String[] argsCon = new String[]{movieName, songName};
      Cursor finderCon = db.rawQuery("Select " + IsPlayedIn._ID
                      + " FROM " + IsPlayedIn._TabellenName + " as I"
                      + " WHERE I." + IsPlayedIn._MovieName + " = ?"
                      + " AND I." + IsPlayedIn._SongName + " = ?"
              , argsCon);
      if (finderCon.getCount() == 0) {
        ContentValues valuesCon = new ContentValues();

        valuesCon.put(IsPlayedIn._MovieName, movieName);
        valuesCon.put(IsPlayedIn._SongName, songName);

        synchronized (db) {
          db.insert(IsPlayedIn._TabellenName, null, valuesCon);
        }
      }

    }

    return result.isEmpty() ? false : true;

  }

  @Override
  protected void postResult(Boolean result) {
    if (result) {
      ((ResultMovieTitleActivity) activity).updateList();
    }
  }

}

