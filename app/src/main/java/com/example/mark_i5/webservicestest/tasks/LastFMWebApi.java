package com.example.mark_i5.webservicestest.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.mark_i5.webservicestest.R;
import com.example.mark_i5.webservicestest.WebServicesActivity;
import com.example.mark_i5.webservicestest.childActivities.ListTracksActivity;
import com.example.mark_i5.webservicestest.data.Track;
import com.example.mark_i5.webservicestest.data.TrackDatabaseAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mark-i5 on 21/08/2014.
 */
public class LastFMWebApi extends AsyncTask<String, Integer, String> {

    private ProgressDialog progressDialog;
    private Context context;
    private WebServicesActivity activity;
    private static final String LOGTAG = "LastFMWebApi";


    public LastFMWebApi(WebServicesActivity activity){
        super();
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(LOGTAG, this.context.toString());
        progressDialog = ProgressDialog.show(this.activity, this.context.getResources().getString(R.string.searching)
                       , this.context.getResources().getString(R.string.searching_desc), true, false );
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Log.d(LOGTAG, "background thread running: " + Thread.currentThread().getName());
            String result = LastFMHelper.downloadFromServer(params);
            return result;
        } catch (LastFMHelper.ApiException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this.progressDialog.dismiss();
        //Log.d(LOGTAG, "response :\n\n" + result);

        String info = "found some results";
        if (result.length() == 0 ){
            info = "could not find any results";
        }
        Toast.makeText(this.context, info, Toast.LENGTH_LONG).show();

        ArrayList<Track> tracks = new ArrayList<Track>();

        try{
            JSONObject jsonObj = new JSONObject(result);
            JSONObject jsonTopTracks = jsonObj.getJSONObject("toptracks");
            JSONArray jsonTracks = jsonTopTracks.getJSONArray("track");

            for (int i = 0 ; i < jsonTracks.length(); i ++){
                JSONObject track = jsonTracks.getJSONObject(i);
                String name = track.getString("name");
                Log.d(LOGTAG,"name: "+ name);
                String artist = track.getJSONObject("artist").getString("name");
                Log.d(LOGTAG,"artist: "+ artist);
                String artistUrl = track.getJSONObject("artist").getString("url");
                Log.d(LOGTAG,"artistUrl: "+ artistUrl);
                String trackUrl = track.getString("url");
                Log.d(LOGTAG,"trackUrl: "+ trackUrl);
                String imgUrl;
                try{
                    imgUrl = track.getJSONArray("image").getJSONObject(1).getString("text");
                }
                catch (Exception e){
                    imgUrl = null;
                }

                Track newTrack = new Track(name,artist,trackUrl,artistUrl,imgUrl, 0);
                tracks.add(newTrack);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        Log.d(LOGTAG, tracks.toString());
        //this.activity.setTracks(tracks);
        TrackDatabaseAdapter tda= new TrackDatabaseAdapter(this.context);
        //String country = this.activity.getSharedPreferences(WebServicesActivity.PREFS_NAME,Context.MODE_PRIVATE).getString(WebServicesActivity.COUNTRY,"");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this.context);
        String country = sp.getString(WebServicesActivity.COUNTRY,"");
        Log.d(LOGTAG, "the country is: " + country);

        for (Track track : tracks){
            tda.insertTrack(track, country);
        }

    }
}
