package org.dhbw.se.movietunes;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import org.dhbw.se.movietunes.controller.SearchByTitleController;
import org.dhbw.se.movietunes.model.Song;
import org.dhbw.se.movietunes.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends MainActivity implements AdapterView.OnItemClickListener {

    ListView mListView;
    SoundtrackSearchResult strackSearchResult;

    List<Song> currentSongList = null;

    Player player;
    Player player2;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View intro;
        TextView searchResult;
        Intent goToNextActivity;
        SearchByTitleController controller;
        Intent intent = getIntent();
        controller = new SearchByTitleController(getApplicationContext());

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);
        String movieTitle = intent.getStringExtra(LookUpSoundtrackActivity.EXTRA_MESSAGE);
        TextView movie = (TextView) findViewById(R.id.movie);
        movie.setText(movieTitle);

        mListView = (ListView) findViewById(R.id.soundtrack_list_view);
// 1
        //List<Song> songArrayList = controller.lookupSoundtrack(movieTitle);
        strackSearchResult = controller.searchTracklist(movieTitle);
        //
        currentSongList = new ArrayList<>(strackSearchResult.getSongs());
// 2
        String[] strings = new String[currentSongList.size()];
// 3
        for (int i = 0; i < currentSongList.size(); i++) {
            Song song = currentSongList.get(i);
            strings[i] = song.getSongTitle() + " (Duration:" + song.getDuration() + ")" + song.getSinger();
        }
// 4
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(this);


    }


    public PopupMenu showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        return popup;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        // ListView Clicked item index
        int itemPosition = position;

        // ListView Clicked item value
        String songString = (String) mListView.getItemAtPosition(position);

        final String trackId = currentSongList.get(position).getTrackId();
        //final String uri=

        //System.out.println("It works "+uri);
        //Todo Buttons to play on Spotify or on Youtube
        PopupMenu popupMenu=showPopup(mListView.getChildAt(itemPosition));
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                String title=(String) item.getTitle();
                if(title.contains("Spotify")){
                   //player=new SpotifyPlayer(currentSongList.get(position).getSongTitle(), uri);
                    //url from search request!!

                    String url=strackSearchResult.getUrl();

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                }
                if(title.contains("Youtube")){
                   // player=new YoutubePlayer(currentSongList.get(position).getSongTitle());

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.de")));
                }
                if(title.contains("similar")){
                    Intent intent = new Intent(getApplicationContext(), SimilarSongsActivity.class);
                    intent.putExtra("TRACK_ID", trackId);
                    startActivity(intent);

                }
                Toast.makeText(SearchResultActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                
                return true;
            }
        });

        popupMenu.show();//showing popup menu


        //player=new SpotifyPlayer(currentSongList.get(position).getSongTitle(), uri);
       // player2=new YoutubePlayer(currentSongList.get(position).getSongTitle());
        //player.play();
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com")));


        // Show Alert
        // Toast.makeText(getApplicationContext(),
        //         "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
        //         .show();

        //Intent intent = new Intent(getApplicationContext(), SimilarSongsActivity.class);
        //intent.putExtra("TRACK_ID", trackId);
        //startActivity(intent);
    }
}


