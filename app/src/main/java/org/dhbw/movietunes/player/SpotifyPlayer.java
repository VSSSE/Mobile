package org.dhbw.movietunes.player;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import org.dhbw.movietunes.R;
import org.dhbw.movietunes.database.Database;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.Song;

import java.util.ArrayList;

public class SpotifyPlayer extends Player {
  String preUri = "";
  SpotifyCommunication spotifyCommunication;

  public SpotifyPlayer(Activity activity, String uri) {
    super(activity);
    this.preUri = uri;

  }

  @Override
  public String createUri(String searchString, String searchString2) {
    if (preUri == null || (preUri != null && preUri.isEmpty())) {
      spotifyCommunication = new SpotifyCommunication();


      Song result = spotifyCommunication.getSong(searchString);

      if(result == null)
      {
          Toast.makeText(activity, R.string.noSpotify,
                  Toast.LENGTH_LONG).show();

          //return "https://open.spotify.com/track/08gu2ff02on5fIISMGLg0S?si=sBqPzW7WQiGlCZFoU0M8JA";
          return null;
      } else {


        ContentValues values = new ContentValues();
        values.put(Song._Artist, result.getArtist());
        values.put(Song._Duration, result.getDuration());
        values.put(Song._ImageUri, result.getImageUri());
        //TODO update connection tabels
        //values.put(Song._SongTitle, result.getSongTitle());
        values.put(Song._TrackId, result.getTrackId());
        values.put(Song._Uri, result.getUri());

        String selection = Song._SongTitle + " = ?";

        String[] args = new String[]{searchString};
        SQLiteDatabase dbUpChat = Database.getDB(activity);

        synchronized (dbUpChat) {
          dbUpChat.update(
                  Song._TabellenName,
                  values,
                  selection,
                  args);
        }



        return result.getUri();

      }

    }
    else
    {
      return preUri;
    }

  }

}
