package com.example.mark_i5.webservicestest.childActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mark_i5.webservicestest.R;
import com.example.mark_i5.webservicestest.data.Track;

import java.util.ArrayList;

public class ListTracksActivity extends Activity {

    private ArrayList<Track> tracks;
    public void setTracks(ArrayList<Track> tracks) {

        this.tracks = tracks;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tracks);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_tracks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MyViewHolder{
        public TextView artistName, trackName;
        public ImageView icon;
        public Button trackButton;
        public Track track;
    }
}
