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
import java.util.HashMap;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.http.ImageLoader;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.player.SpotifyPlayer;
import org.dhbw.movietunes.player.YoutubePlayer;

public class MovieSoundtracksAdapter extends BaseAdapter {

  private static LayoutInflater inflater = null;
  public ImageLoader imageLoader;
  private Activity activity;
  private ArrayList<Song> data;

  public MovieSoundtracksAdapter(Activity a, ArrayList<Song> d) {
    activity = a;
    data = d;
    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    imageLoader = new ImageLoader(activity.getApplicationContext());
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
      vi = inflater.inflate(R.layout.list_row, null);

    TextView title = vi.findViewById(R.id.title); // title
    TextView artist = vi.findViewById(R.id.artist); // artist name
    TextView duration = vi.findViewById(R.id.duration); // duration
    ImageView thumb_image = vi.findViewById(R.id.list_image); // thumb image

    final Song song = data.get(position);

    // Setting all values in listview
    title.setText(song.getSongTitle());
    artist.setText(song.getArtist());
    duration.setText(song.getDuration());
    imageLoader.DisplayImage(song.getImageUri(), thumb_image);


    vi.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PopupMenu popupMenu =  new PopupMenu(activity, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu_movie_soundtracks, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
          public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
              case R.id.spotify:
                new SpotifyPlayer(activity, song.getSongTitle(), song.getUri());
                break;
              case R.id.youTube:
                new YoutubePlayer(activity, song.getSongTitle());
                break;
              case R.id.similar:
                Intent intent = new Intent(activity.getApplicationContext(), ResultSimilarSongsActivity.class);
                intent.putExtra("TRACK_ID", song.getTrackId());
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
    });
    return vi;
  }


}