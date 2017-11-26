package com.example.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.dhbw.se.movietunes.logic.Controller;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class SearchResultActivityList extends ListActivity{
    ListView listView;
    Intent intent;
    Controller c;

        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.list_soundtrack);
            listView = (ListView) findViewById(R.id.list);

            String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                    "Linux", "OS/2" };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.activity_list_item, values);
            setListAdapter(adapter);

            listView.setAdapter(adapter);

            // ListView Item Click Listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }


            });
        }

}


