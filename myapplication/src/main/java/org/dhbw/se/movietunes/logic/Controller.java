package org.dhbw.se.movietunes.logic;

import com.example.myapplication.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class Controller {

   // private static Controller instance = null;

    private Controller(){
    }


    public List<Song> lookupSoundtrack(String input){
        List<Song> soundtrack=new ArrayList<Song>();
        soundtrack.add(new Song("Song11"));
        soundtrack.add(new Song("Song12fff"));
        soundtrack.add(new Song("Song13sss"));
        soundtrack.add(new Song("Song14ggg"));
        return soundtrack;

    }

    public List<Song> findSimilarSongs(String songId){
        List<Song> similarSongs=new ArrayList<Song>();
        similarSongs.add(new Song("Song11"));
        similarSongs.add(new Song("Song12fff"));
        similarSongs.add(new Song("Song13sss"));
        similarSongs.add(new Song("Song14ggg"));
        return similarSongs;

    }
    
    public void findSoundtrackByMovieTitle(String input){
        List<Song> soundTracks=new ArrayList<Song>();
    }
    public double getTotalTime(List<Song>allSongs){
        double x=0;
        for(Song song:allSongs){
            x+=Double.parseDouble(song.getTime());
        }
        return x;
    }

    //public static Controller getInstance() {
      //  if (instance==null){
           // instance = new Controller();
        }
       // return instance;
   // }



//}
