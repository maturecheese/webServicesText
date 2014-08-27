package com.example.mark_i5.webservicestest.adapters;


import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mark_i5.webservicestest.R;
import com.example.mark_i5.webservicestest.childActivities.ListTracksActivity;
import com.example.mark_i5.webservicestest.childActivities.ListTracksActivity.MyViewHolder;

import com.example.mark_i5.webservicestest.data.Track;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by marklloyd on 21/08/2014.
 */
public class TrackDataAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String LOGTAG = "TrackDataAdapter";
    private ListTracksActivity listTracksActivity;
    private LayoutInflater layoutInflater;
    private ArrayList<Track> tracks;


    public TrackDataAdapter(LayoutInflater layoutInflater, ListTracksActivity listTracksActivity, ArrayList<Track> tracks){
        this.layoutInflater = layoutInflater;
        this.listTracksActivity = listTracksActivity;
        this.tracks = tracks;
    }
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.track_row, parent, false);
            holder = new MyViewHolder();
            holder.artistName = (TextView) convertView.findViewById(R.id.artist_name);
            holder.trackName = (TextView) convertView.findViewById(R.id.track_name);
            holder.icon = (ImageView) convertView.findViewById(R.id.album_icon);
            holder.trackButton = (Button)convertView.findViewById(R.id.track_button);
            holder.trackButton.setTag(holder);
            convertView.setTag(holder);
        }   else{
            holder = (MyViewHolder)convertView.getTag();
        }
        Track track = tracks.get(position);
        holder.track =track;
        holder.trackName.setText(track.getName());
        holder.artistName.setText(track.getArtist());
        convertView.setOnClickListener(this);



        return convertView;
    }


    @Override
    public void onClick(View view) {
        MyViewHolder holder = (MyViewHolder)view.getTag();
       Log.d(LOGTAG, "onClick pressed on view");
        if (view instanceof Button) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(holder.track.getArtistUrl()));
            this.listTracksActivity.startActivity(intent);
        }else if (view instanceof View){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(holder.track.getTrackUrl()));
            this.listTracksActivity.startActivity(intent);

        }


    }
}
