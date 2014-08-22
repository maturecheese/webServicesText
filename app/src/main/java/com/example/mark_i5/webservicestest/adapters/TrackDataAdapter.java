package com.example.mark_i5.webservicestest.adapters;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.mark_i5.webservicestest.childActivities.ListTracksActivity;
import com.example.mark_i5.webservicestest.data.Track;

import java.util.ArrayList;

/**
 * Created by marklloyd on 21/08/2014.
 */
public class TrackDataAdapter extends BaseAdapter implements DialogInterface.OnClickListener{

    private static final String LOGTAG = "TrackDataAdapter";
    private ListTracksActivity listTracksActivity;
    private LayoutInflater layoutInflater;
    private ArrayList<Track> tracks;


    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
