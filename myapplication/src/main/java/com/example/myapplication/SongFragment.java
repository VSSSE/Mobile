package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class SongFragment extends Fragment {
    public SongFragment(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
return inflater.inflate(R.layout.activity_search_result, container, false);
    }
}
